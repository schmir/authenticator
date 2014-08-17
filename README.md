# authenticator

authenticator is a small Clojure library that can be used to easily
implement a [java.net.Authenticator](http://docs.oracle.com/javase/8/docs/api/java/net/Authenticator.html).

Authenticators can be asked to provide username and password for
network connections that require them.


## Usage

``` clj
(require '[authenticator.core :as auth])

(defn get-credentials
	[options]
	(println "options" options)
	(when (= (:host options) "localhost")
	  ["user" "password"]))

(auth/set-default-authenticator get-credentials)
(slurp "http://localhost/secret/")
```

The get-credentials function above is called with a single map as
argument. It contains the following keys:


<dl>
  <dt>:host</dt>
  <dd>hostname of the site or proxy requesting authentication, or nil if not available</dd>

  <dt>:port</dt>
  <dd>port number for the requested connection</dd>

  <dt>:prompt</dt>
  <dd>prompt string given by the requestor</dd>

  <dt>:protocol</dt>
  <dd>protocol for the requested connection</dd>

  <dt>:scheme</dt>
  <dd>scheme for the requested connection</dd>

  <dt>:site</dt>
  <dd>InetAddress of the site requesting authorization, or null if not available</dd>

  <dt>:url</dt>
  <dd>URL that resulted in this request for authentication</dd>

  <dt>:requestor-type</dt>
  <dd>:proxy for a Proxy or :server otherwise</dd>
</dl>

The function should return a [username, password] pair to be used with
the connection or nil if it can't supply credentials.

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
