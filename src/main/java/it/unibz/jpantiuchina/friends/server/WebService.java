package it.unibz.jpantiuchina.friends.server;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private static final Map<String, Set<String>>  userFriendsByUserPhone = new HashMap<>();
    private static final Map<String, UserData>     userInfoByUserPhone    = new HashMap<>();


    @GET
    @Path("report-my-location-and-get-my-friend-locations")
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<UserData> getMyFriends(@QueryParam("my-phone") String myPhoneNumber,
                                                    @QueryParam("my-lat")   double myLat,
                                                    @QueryParam("my-lng")   double myLng)
    {
        // First, update my info

        System.out.println("Get friends " + myPhoneNumber + ' ' + myLat + ' ' + myLng);


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


        // Next, find info of all my friends and report it if exists

        ArrayList<UserData> myFriendInfos = new ArrayList<>();

        Set<String> phonesOfMyFriends = userFriendsByUserPhone.get(myPhoneNumber);

        if (phonesOfMyFriends != null)
        {
            for (String myFriendPhoneNumber : phonesOfMyFriends)
            {
                Set<String> myFriendFriends = userFriendsByUserPhone.get(myFriendPhoneNumber);
                UserData myFriendInfo = userInfoByUserPhone.get(myFriendPhoneNumber);

                System.out.println("myFriendInfo " + myFriendInfo);

                if (myFriendInfo != null && myFriendFriends != null && myFriendFriends.contains(myPhoneNumber))
                {
                    myFriendInfos.add(myFriendInfo);
                    System.out.println("YES");
                }
            }
        }

        return myFriendInfos;
    }


    @POST
    @Path("tell-my-friend-phone-numbers")
    @Consumes(MediaType.APPLICATION_JSON)
    public static void registerMe(@QueryParam("my-phone") String myPhoneNumber, Set<String> myFriendList)
    {
        System.out.println("Reg me " + myPhoneNumber + ' ' + Arrays.toString(myFriendList.toArray()));

        userFriendsByUserPhone.put(myPhoneNumber, myFriendList);
    }
}
