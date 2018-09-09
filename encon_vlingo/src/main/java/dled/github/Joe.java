package dled.github;

import io.appulse.encon.Node;
import io.appulse.encon.Nodes;
import io.appulse.encon.config.NodeConfig;
import io.appulse.encon.mailbox.Mailbox;
import io.appulse.encon.terms.ErlangTerm;
import io.appulse.encon.terms.type.ErlangPid;
import io.appulse.epmd.java.core.model.NodeType;
import io.vlingo.actors.Actor;
import io.vlingo.actors.Completes;
import io.vlingo.actors.Stoppable;

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
    public Completes<Stoppable> waitForCall() {
        ErlangTerm payload = mailbox.receive().getBody();

        ErlangPid from = payload.get(0)
                .filter(ErlangTerm::isPid)
                .map(ErlangTerm::asPid)
                .orElseThrow(() -> new RuntimeException("Expected first term to be a PID"));

        String text = payload.get(1)
                .filter(ErlangTerm::isBinary)
                .map(ErlangTerm::asText)
                .orElseThrow(() -> new RuntimeException("Expected second term to be binary"));

        System.out.format("Joe: received '%s' from %s\n", text, from);

        // reply
        mailbox.send(from, string("Hello, Mike!"));

        System.out.flush();

        // hang up
        node.close();
        System.out.println("Node closed");

        return completes().with(this);
    }
}
