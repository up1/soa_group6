# Get user information by ID

This can be run with `silk -silk.url="http://localhost:9001/"`

## `GET user?page=1`

Perform a find user information with id `1`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=utf8"`
 ```
{
	"id":555,
	"firstname":"555",
	"lastname":"555"
}
 ```