# Design Plan

Design three objects to represent the relationship. User class represents the user information.
The Role class represents the role information. The UserMapRole class is the bridge between User 
and Role class.

Design three interfaces to implement the specific function. The UserService is to implement user 
creation and delete. The RoleService is to implement role creation and delete and bind the user 
and the role. IdentityService is to authenticate the user, invalidate the token and check if the 
user binds with a certain role.

Use the strategy pattern to get the service. Use `ConcurrentHashMap` to store the different data.
Use `AtomicInteger` as the ID　generator, this is only suitable for the single instance. Use `SHA-256`
to encode the user password, and generate the token based on userId and password. The token will
be valid since it's generated in two hours.

In order to  handle the business exceptions or errors, define three exception classes CheckRoleException,
 RoleException, UserException. 

# Test
UserServiceTest, RoleServiceTest and IdentityServiceTest test their own methods to check their 
correct.  AppTest tests the whole application. Running `testAllTokenValid` method will test whole
workflow under the token is valid, otherwise, running `testAllTokenInValid` method will test whole
workflow under the token is invalid, the `CheckRoleException` will be thrown.

# Next Step

Implement customized Servlet server based on Java NIO. So call the API same as the application runs
in Tomcat web server.

 