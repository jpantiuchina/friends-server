package it.unibz.jpantiuchina.friends.server;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/")
public final class WebService
{
    private static final Map<String, List<String>> userFriendsByUserPhone = new HashMap<>();
    private static final Map<String, UserData>     userInfoByUserPhone    = new HashMap<>();


    @GET
    @Path("report-my-location-and-get-my-friend-locations")
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<UserData> getMyFriends(@QueryParam("my-phone") String myPhoneNumber,
                                                    @QueryParam("my-lat")   double myLat,
                                                    @QueryParam("my-lng")   double myLng)
    {
        // First, update my info

        UserData myInfo = userInfoByUserPhone.get(myPhoneNumber);

        if (myInfo == null)
        {
            myInfo = new UserData();
            myInfo.setPhoneNumber(myPhoneNumber);
            userInfoByUserPhone.put(myPhoneNumber, myInfo);
        }

        myInfo.setLastUpdated(Instant.now());
        myInfo.setLat(myLat);
        myInfo.setLng(myLng);


        // Next, find my info of all my friends info and report it if exists

        List<UserData> myFriendInfos = new ArrayList<>();

        List<String> myFriendPhoneNumbers = userFriendsByUserPhone.get(myPhoneNumber);

        if (myFriendPhoneNumbers != null)
        {
            for (String myFriendPhoneNumber : myFriendPhoneNumbers)
            {
                UserData myFriendInfo = userInfoByUserPhone.get(myFriendPhoneNumber);
                if (myFriendInfo != null)
                    myFriendInfos.add(myFriendInfo);
            }
        }

        return myFriendInfos;
    }


    @POST
    @Path("tell-my-friend-phone-numbers")
    @Consumes(MediaType.APPLICATION_JSON)
    public static void registerMe(@QueryParam("my-phone") String myPhoneNumber, List<String> myFriendList)
    {
        for (String myFriend : myFriendList)
        {
            System.out.println("Reg me " + myPhoneNumber + ' ' + myFriend);

        }
        userFriendsByUserPhone.put(myPhoneNumber, myFriendList);
    }
}
