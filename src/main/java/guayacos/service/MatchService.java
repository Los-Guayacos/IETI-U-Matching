package guayacos.service;

import java.util.List;
import guayacos.model.entities.Match;

public interface MatchService {
    List<Match> fetchAll(String userId);
    List<Match> unmatch(String userId, String userToUnmatch);
}

