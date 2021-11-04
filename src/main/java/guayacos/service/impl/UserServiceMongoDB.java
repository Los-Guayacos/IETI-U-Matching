package guayacos.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.Query.Direction;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import guayacos.model.entities.Interest;
import guayacos.model.entities.Match;
import guayacos.model.entities.User;
import guayacos.model.helpers.Filter;
import guayacos.service.UserService;
import org.springframework.stereotype.Component;



@Component
public class UserServiceMongoDB implements UserService {

    @Override
    public void createUser(User user) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            user.setVerified(false);
            user.setActive(false);
            user.setRating(5);
            user.setTotalRatings(1);
            user.setRegistrationDate(new Date());
            user.setLikes(new ArrayList<String>());
            user.setReceivedLikes(new ArrayList<String>());
            user.setMatches(new ArrayList<Match>());
            user.setInterests(new ArrayList<Interest>());
            user.setRooms(new HashMap<String, String>());
            user.setRatings(new HashMap<String, Integer>());
            ApiFuture<WriteResult> future = db.collection("users").document(user.getUid()).set(user);
            System.out.println(future);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> fetchAll(int limit, String uid) {
        List<User> users = new ArrayList<User>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            Query ref = db.collectionGroup("users").limitToLast(limit).orderBy("registrationDate",
                    Direction.DESCENDING);
            ApiFuture<QuerySnapshot> querySnapshot = ref.get();
            for (DocumentSnapshot user : querySnapshot.get().getDocuments()) {
                User userRetrieved = user.toObject(User.class);
                if (!userRetrieved.getUid().equals(uid))
                    users.add(userRetrieved);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public List<User> fetchCustom(int limit, String userId, Filter filters) {
        List<User> users = new ArrayList<User>();
        try {

            User currentUser = findUserById(userId);
            if (currentUser != null) {
                Firestore db = FirestoreClient.getFirestore();
                Query ref = db.collectionGroup("users").limitToLast(limit).orderBy("registrationDate",
                        Direction.DESCENDING);
                ApiFuture<QuerySnapshot> querySnapshot = ref.get();
                for (DocumentSnapshot user : querySnapshot.get().getDocuments()) {
                    User retrievedUser = user.toObject(User.class);
                    // ¿Tiene filtros la consulta?
                    if (filters != null) {
                        if (validUserByFilter(filters, retrievedUser, currentUser)) {
                            users.add(retrievedUser);
                        }
                    } else {
                        // Filtrar los que ya estan en match o estan bloqueados
                        List<String> likes = currentUser.getLikes() != null ? currentUser.getLikes()
                                : new ArrayList<String>();
                        if (likes.contains(retrievedUser.getUid())
                                || retrievedUser.getUid().equals(currentUser.getUid())) {
                            users.add(retrievedUser);
                        }
                    }
                }
            } else {
                System.out.println("El usuario no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(users);
        return users;
    }
    private boolean validUserByFilter(Filter filter, User user, User owner) {
        boolean isValid = true;
        List<String> likes = owner.getLikes() != null ? owner.getLikes() : new ArrayList<String>();
        // No matches no likes
        if (likes.contains(user.getUid()) || user.getUid().equals(owner.getUid())) {
            isValid = false;
            System.out.println("No valid for id");
        }
        // Age range (Less than ...)
        if (filter.getAge() != 0 && filter.getAge() > 0 && filter.getAge() < user.getAge()) {
            isValid = false;
            System.out.println("No valid for age");
        }
        // For gender
        if (!filter.getGender().toLowerCase().equals("") && !filter.getGender().toLowerCase().equals("todos")
                && !filter.getGender().toLowerCase().equals(user.getGender().toLowerCase())) {
            isValid = false;
            System.out.println("No valid for gender");
        }
        // For college
        if (!filter.getCollege().toLowerCase().equals("")
                && !filter.getCollege().toLowerCase().equals(user.getCollege().toLowerCase())) {
            isValid = false;
            System.out.println("No valid for college");
        }
        // For program
        if (!filter.getProgram().toLowerCase().equals("") && !filter.getProgram().toLowerCase().equals("todos")
                && !filter.getProgram().toLowerCase().equals(user.getProgram().toLowerCase())) {
            isValid = false;
            System.out.println("No valid for program");
        }
        // For rating
        if (filter.getRating() >= 0 && filter.getRating() > user.getRating()) {
            isValid = false;
            System.out.println("No valid for rating");
        }
        // For interest
        if (filter.getInterests()) {
            boolean hasCommonInterest = false;
            for (Interest interest : user.getInterests()) {
                String name = interest.getName().toLowerCase();
                for (Interest interestOwner : owner.getInterests()) {
                    if (name.equals(interestOwner.getName().toLowerCase())) {
                        hasCommonInterest = true;
                    }
                }
            }
            if (!hasCommonInterest) {
                isValid = false;
                System.out.println("No valid for interest");
            }
        }
        return isValid;
    }


    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try {
            Firestore db = FirestoreClient.getFirestore();
            Query ref = db.collection("users").whereEqualTo("email", email);
            ApiFuture<QuerySnapshot> querySnapshot = ref.get();

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                user = document.toObject(User.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findUserById(String uid) {
        User user = null;
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference ref = db.collection("users").document(uid);
            ApiFuture<DocumentSnapshot> future = ref.get();
            DocumentSnapshot documentSnapshot = future.get();
            if (documentSnapshot.exists()) {
                user = documentSnapshot.toObject(User.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User updateUser(User user) {
        User userUpdated = null;
        try {
            if (user != null) {
                Firestore db = FirestoreClient.getFirestore();
                DocumentReference ref = db.collection("users").document(user.getUid());
                ApiFuture<WriteResult> future = ref.set(user);
                System.out.println(future);
                userUpdated = user;
            } else {
                System.out.println("Can't update null");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userUpdated;
    }
/*
    private final UserRepository userRepository;
    public UserServiceMongoDB(@Autowired UserRepository userRepository){
        this.userRepository=userRepository;
    }



    @Override
    public List<User> getAll()
    {
        return userRepository.findAll();
    }


    @Override
    public User findById( String id )
    {
        Optional<User> optionalUser = userRepository.findById( id );
        if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            throw new UserNotFoundException();
        }
    }
    @Override
    public User findByUserName(String userName) throws  UserNotFoundException{

        Optional<User> optionalUser = userRepository.findByUserName(userName);
        System.out.println(optionalUser);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new UserNotFoundException();
        }


    }
    @Override
    public User findByEmail( String email )
            throws UserNotFoundException
    {
        Optional<User> optionalUser = userRepository.findByEmail( email );
        if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            throw new UserNotFoundException();
        }
    }
    @Override
    public List<User> findByGenderMan()
            throws UserNotFoundException
    {
        List<User> optionalUser = userRepository.findByGender( "Hombre" );
        return optionalUser;*/
        /*if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            throw new UserNotFoundException();
        }*/
    /*}
    public List<User> findByGenderWoman()
            throws UserNotFoundException {
        List<User> optionalUser = userRepository.findByGender("Mujer");
        return optionalUser;
    }

    @Override
    public User update(UserDto userDto, String id) {
        if ( userRepository.findById( id ).isPresent() )
        {
            User user = userRepository.findById( id ).get();
            user.update( userDto );
            userRepository.save( user );
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        if ( userRepository.existsById( id ) )
        {
            userRepository.deleteById( id );
            return true;
        }
        return false;
    }*/


}
