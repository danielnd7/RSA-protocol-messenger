package RSA_messenger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    @DisplayName("New user is added to the server's map")
    @Test
    void givenAUserThenAddedCorrectly(){
        // Arrange
        PublicUser user1 = new PublicUser("Lucas", new KeyPair(2,5));
        Server server = new Server();

        // Act
        server.addUser(user1);

        // Assert
        assertTrue(server.getUsersSet().contains(user1));
    }

    @DisplayName("Two users in the server are returned correctly by getUsersSet")
    @Test
    void givenTwoUsersWhenTryToGetUsersSetThenReturnsCorrectly(){
        // Arrange
        PublicUser user1 = new PublicUser("Lucas", new KeyPair(2,5));
        PublicUser user2 = new PublicUser("Danichelo", new KeyPair(1,6));
        Server server = new Server();

        Set<PublicUser> resultSet = new TreeSet<>();
        resultSet.add(user1);
        resultSet.add(user2);

        // Act
        server.addUser(user1);
        server.addUser(user2);

        // Assert
        assertEquals(resultSet, server.getUsersSet());
    }

    @DisplayName("getUsersSet returns correctly sorted by name set")
    @Test
    void givenTwoUsersWhenTryToGetUsersSetThenReturnsCorrectlySortedSet(){
        // Arrange
        PublicUser user1 = new PublicUser("Lucas", new KeyPair(2,5));
        PublicUser user2 = new PublicUser("Danichelo", new KeyPair(1,6));
        Server server = new Server();

        // Act
        server.addUser(user1);
        server.addUser(user2);

        SortedSet<PublicUser> usersSet = (SortedSet<PublicUser>) server.getUsersSet();
        PublicUser dani = usersSet.first();

        // Assert
        assertEquals("DANICHELO", dani.getUserName());
    }

}