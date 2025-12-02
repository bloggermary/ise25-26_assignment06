package de.seuhd.campuscoffee.tests.system;

public class UsersSystemTests extends AbstractSysTest {

        @Test
        void createUser() {
            User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
            User createdUser = userDtoMapper.toDomain(
                    userRequests.create(
                            List.of(userDtoMapper.fromDomain(userToCreate))
                    ).getFirst()
            );

            assertEqualsIgnoringIdAndTimestamps(createdUser, userToCreate);
        }

        @Test
        void getUserById() {

            User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
            User createdUser = userDtoMapper.toDomain(
                    userRequests.create(
                            List.of(userDtoMapper.fromDomain(userToCreate))
                    ).getFirst()
            );


            User fetchedUser = userDtoMapper.toDomain(
                    userRequests.getById(createdUser.id())
            );


            assertEqualsIgnoringIdAndTimestamps(fetchedUser, userToCreate);
        }


        @Test
        void updateUser() {

            User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
            User createdUser = userDtoMapper.toDomain(
                    userRequests.create(
                            List.of(userDtoMapper.fromDomain(userToCreate))
                    ).getFirst()
            );


            User updatedUserDomain = createdUser.toBuilder()
                    .username("updated-username")
                    .email("updated@email.com")
                    .build();

            User updatedUser = userDtoMapper.toDomain(
                    userRequests.update(
                            createdUser.id(),
                            userDtoMapper.fromDomain(updatedUserDomain)
                    )
            );

            /
            assertEquals(updatedUserDomain.username(), updatedUser.username());
            assertEquals(updatedUserDomain.email(), updatedUser.email());
        }

    }

