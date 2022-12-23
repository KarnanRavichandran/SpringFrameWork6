Go to Talend Api tester,

Set Method = POST
Set Path = http://localhost:8080/authenticate

in Body,
{
  "username" : "    "
  "password" : "    "
}

You get a response,
{
  "token" : "    "
}

------

Now you can use the token to access any links -rest api

Add the method and path,
now add Header = Authorization and Value = Bearer<space>Token 
Send the request now.