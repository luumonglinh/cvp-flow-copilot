package com.example.cvp.framework;

/** Finds the agent queue that should receive a transferred call. */
public interface AgentRouter {

    /**
     * Returns the queue name for the given call reason.
     *
     * @throws IllegalStateException if no queue is available
     */
    String findQueue(String callReason);
}
