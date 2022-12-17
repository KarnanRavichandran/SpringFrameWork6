When we need to implement a breaking change, its where we should version our api.

Types: URL, Request Parameter, Header and Media Type.

We should make the old version also available to the consumer even after new version is released. 
if not, the code on the customer client must be changed to newer version to support new version.

URL, 
	@GetMapping("/v1/person")
	@GetMapping("/v2/person")

	URLto type-in = /v1/person

Request Parameter,
	@GetMapping(path="/person", params ="version=1")
	@GetMapping(path="/person", params ="version=2")

	URLto type-in = /person?version=1

Header,
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")

	Go to talend api tester, 
		Add the URL = /person/header
		Add Header = X-API-VERSION
		Add value = 1 (or) 2

Media Type,
	@GetMapping(path="/person/accept",produces="application/vnd.company.app-v1+json")
	@GetMapping(path="/person/accept",produces="application/vnd.company.app-v2+json")

	Go to talend api tester, 
		Add the URL = /person/accept
		Add Header = Accept
		Add value = application/vnd.company.app-v1+json