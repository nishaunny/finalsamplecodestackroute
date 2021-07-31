package com.stackroute.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.demo.exception.LeagueAlreadyExistsException;
import com.stackroute.demo.model.LeagueInfo;
import com.stackroute.demo.model.User;
import com.stackroute.demo.repository.leaguerepo;

@Service
public class LeagueServImpl implements LeagueService {

	@Autowired
	leaguerepo Leaguerepos;

	@Override
	public boolean addLeagueDetails(String userName, LeagueInfo[] leagues) throws LeagueAlreadyExistsException {
		List<LeagueInfo> existingLeagues = new ArrayList<>();
		Optional<User> userExist = Leaguerepos.findById(userName);
		if (userExist.isPresent()) {
			for (var leagueinfo : leagues) {
				/* checking and getting user object */

				/* getting count on league exists or not */
				Predicate<LeagueInfo> leaguePred = league -> league.getLeagueName()
						.equalsIgnoreCase(leagueinfo.getLeagueName());
				Predicate<LeagueInfo> teamPred = league -> league.getTeamName()
						.equalsIgnoreCase(leagueinfo.getTeamName());
				long leagueCount = userExist.get().getLeagues().stream().filter(leaguePred.and(teamPred)).count();

				/* add leagueinfo to existing list if count is 0 */
				if (leagueCount == 0) {
					userExist.get().getLeagues().add(leagueinfo);
					Leaguerepos.save(userExist.get());
				} else {
					existingLeagues.add(leagueinfo);
				}
			}
			if (existingLeagues.size() > 0) {
				throw new LeagueAlreadyExistsException("These leagues already exists :" + existingLeagues);
			} else {
				return true;
			}
		}
		User user = new User();
		user.setUserName(userName);
		List<LeagueInfo> leagueList = new ArrayList(Arrays.asList(leagues));

		user.setLeagues(leagueList);
		if (Leaguerepos.insert(user) != null) {
			return true;
		}
		return false;
	}
	
	

}
