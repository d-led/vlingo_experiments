# https://www.youtube.com/watch?v=xrIjfIjssLE
defmodule Mike do
    def call_joe do
        dial()
    end

    defp dial(attempt \\ 0)
    defp dial(22), do: IO.puts "hanging up"

    defp dial(attempt) do
        IO.puts "Mike: beep"

        try_talk_to(Node.connect(:joe@localhost), attempt)
    end

    defp try_talk_to(true, _) do
        send({:joe,:joe@localhost}, {self(),"Hello, Joe!"})
        receive do
            m -> IO.puts "Mike: received from Joe: #{m}"
        end
    end

    defp try_talk_to(_, attempt) do
        :timer.sleep(1000)
        dial(attempt+1)
    end
end

Mike.call_joe()