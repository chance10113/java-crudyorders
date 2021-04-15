package lambda.school.javaordersproject.services;

import lambda.school.javaordersproject.models.Agent;

public interface AgentServices {
    Agent save(Agent agent);
    Agent findAgentById(long id);
}
