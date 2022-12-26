Add Spring-security dependency,

Run the application,
now we get the generated password in log.

Now go to 8080, for username type'user' and password is the generated password.

By default, we are provided with a form based auth (login and logout pages by spring security).

Spring security authenticate every every requests that comes in.

Go to applicaton.properties,
spring.security.user.name = YourUsername
spring.security.user.password = YourPassword

Now you can use your custom username and password.





<----------Getting along with basic authentication---------->

Go to talend api tester,
In the header tab, click on the key symbol named add authorization, now type your custom username and password and set it.

now in the header tab, theres a authorization header with value "Basic " with Base64Encoded Version of your username and password.


By default spring security only allows get methods, to make update requests like put and post, we need csrf tokens along the request.
(Note: Major flaw, the encoded value can be decoded and the credentials can be exposed)


<----------Cross site request forgery (CSRF)----------> 


When we access a webiste and switching to another website without logging out or ending the session, if the other website is a malicious website it can use the cookie to send a request to the previous website you visited.

To avoid csrf, we need to need to introduce synchronizer token pattern, if the user makes a post/put request, we need to authorize them using the tokens.

@GetMapping("/csrf")
    public CsrfToken retrieveCsrfToken(HttpServletRequest httpServletRequest){
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }
This return the csrf token in json format.

Now,lets go to talend api tester,
Add the header as X-CSRF-TOKEN and add the token in value.
Now you can send POST requests.

(Note: CSRF is connected to a system with state, dependent on session based application. We need to disable csrf if we are using stateless application.)

There's another solution, using same site cookie - all we need to do is configure it in application.properties file.
server.servlet.session.cookie.same-site=strict



<----------CSRF DISABLE---------->

Lets do CSRF disable, if our application is stateless,
we need to override default security configuration -> SecurityFilterChainConfiguration

@Configuration
public class BasicAuthSecurityConfiguration{
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(
			auth-> {auth.anyRequest().authenticated();}
		);
		http.httpBasic();	//BasicAuthPopUp window for login
		http.csrf().disable();
		return http.build();
	}
}

now,we can send post/put request without csrf token and no html login and logout form.


<----------Cross Origin Resource Sharing (CORS)---------->	

By default, our browser only makes the resource access within the same domain(eg. only 8080).

if we want to access another domain resource(like request from 3000 to 8080), we need to configure which cross origin requests are allowed. 
- lets configure addCorsMappings
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:3000");
			}
		};
	}
- or we can use @CrossOrigin above the rest controller methods
		@CrossOrigin(origins="specific domain") - specific origin



  

<----------User credentials---------->
method 1:

we setup up username and password inside the application.properties file, and we can save only "One user credentials".  //in-memory
also, we can do in-memory configuration for multiple users by,

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("paul")
                .password("{noop}timo")				// password is stored, not in a encrypted way({noop} means No operations)
                .authorities("read")
                .roles("USER")
                .build();
	var admin= User.withUsername("admin")
                .password("{noop}password")
                .authorities("read")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

(NOTE: in-memory user credentials are just for testing purpose and not for production)

method 2:

Setting up User credentials in a "DATABASE" using JDBC,
Add H2 and JDBC dependencies,
	Make sure you disable frameoptions in securityFilterChain, by default spring security disables html to get loaded so we cannot access H2 console.
	Add this line in basicAuthSecurityConfiguration, http.headers().frameOptions().sameOrigin();
Double tap shift key and search for JdbcDaoImpl class and you can see DEFAULT_USER_SCHEMA_DDL_LOCATION variable with a path value, right click on the path and click on selected file on project pane on left side
the path is where user.ddl(data definition language) file is present, we need to create our schema at the startup of our application.


First we are configuring the h2 and making sure of the ddl file getting loaded up at startup.

@Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }


Now, the user.ddl is ready we need to insert our credentials,
@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var user = User.withUsername("paul")
                .password("{noop}timo")
                .authorities("read")
                .roles("USER")
                .build();
        var admin= User.withUsername("admin")
                .password("{noop}password")
                .authorities("read")
                .roles("ADMIN")
                .build();
       var jdbcUserDetailsManager= new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;

    }
All done, now you can login using credentials stored in a in-memory database using Jdbc.




<----------Securing passwords---------->

Every systems are evolving and we need to secure our application by making a adaptive one way function with work factor of 1 second,

Encoding-> Converting data into encoded form and using a decoding system to decode the data, theres no security in it, theyre just transferring the data.(Like Base64,..)
Encryption-> It works the same way, data is converted into encrypted form, a decryption system uses a key or password and the data can be extracted.(Like RSA)
Hashing-> Data is converted into hash and it will be sent along the data, the other side converts the data into hashes and checks both the hashes, 
its just used for checking integrity of data.(Like BCyrpt,SCrypt,....)

Lets secure our application using BCrypt,

in BasicAuthSecurityConfiguration,

Add the bean,

	@Bean
    	public BCryptPasswordEncoder bCryptPasswordEncoder(){
       	 	return new BCryptPasswordEncoder(10);
    	}

and for user credentials,

	.password("timo")						//remove{noop}
        .passwordEncoder(s -> bCryptPasswordEncoder().encode(s))	//Add this line

Now go to h2-console and check the user table, now you can see the password in a different form, like $2a..........




