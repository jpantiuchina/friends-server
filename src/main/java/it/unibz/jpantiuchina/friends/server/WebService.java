package it.unibz.jpantiuchina.friends.server;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/get-my-friends")
public final class WebService
{
    private static final Map<String, Map<String, UserData>> groups = new HashMap<String, Map<String, UserData>>();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<UserData> getMyFriends(@QueryParam("group") String groupName,
                                                    @QueryParam("name")  String userName,
                                                    @QueryParam("lat")   double lat,
                                                    @QueryParam("lng")   double lng)
    {
        // Get the group by the group name or create if null
        Map<String, UserData> group = groups.get(groupName);
        if (group == null)
        {
            group = new HashMap<String, UserData>();
            groups.put(groupName, group);
        }

        // Get the user from the group by name or create
        UserData user = group.get(userName);
        if (user == null)
        {
            user = new UserData();
            user.setName(userName);
            group.put(userName, user);
        }

        // Update user details
        user.setLastUpdated(Instant.now());
        user.setLat(lat);
        user.setLng(lng);

        // Return all group to
        return group.values();
    }
}
