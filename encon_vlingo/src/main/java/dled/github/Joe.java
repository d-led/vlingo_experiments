package dled.github;

import io.appulse.encon.Node;
import io.appulse.encon.Nodes;
import io.appulse.encon.config.NodeConfig;
import io.appulse.encon.mailbox.Mailbox;
import io.appulse.encon.terms.ErlangTerm;
import io.appulse.encon.terms.type.ErlangPid;
import io.appulse.epmd.java.core.model.NodeType;
import io.vlingo.actors.Actor;

import static io.appulse.encon.terms.Erlang.string;

// https://www.youtube.com/watch?v=xrIjfIjssLE
public class Joe extends Actor implements Listener {
    final Node node;
    final Mailbox mailbox;

    public Joe() {
        NodeConfig config = NodeConfig.builder()
                .shortNamed(true)
                .type(NodeType.R4_ERLANG)
                .build();

        node = Nodes.singleNode("joe@localhost", config);

        mailbox = node.mailbox()
                .name("joe")
                .build();
    }

    @Override
    public void waitForCall() {
        ErlangTerm payload = mailbox.receive().getBody();

        ErlangPid from = payload.get(0)
                .filter(ErlangTerm::isPid)
                .map(ErlangTerm::asPid)
                .orElseThrow(() -> new RuntimeException("Expected first element is PID"));

        String text = payload.get(1)
                .filter(ErlangTerm::isBinary)
                .map(ErlangTerm::asText)
                .orElseThrow(() -> new RuntimeException("Expected second element is binary"));

        System.out.format("Joe: received '%s' from %s\n", text, from);

        // reply
        mailbox.send(from, string("Hello, Mike!"));

        // hang up
        node.close();
        this.stop();
    }
}
