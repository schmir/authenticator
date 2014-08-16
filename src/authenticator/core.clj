(ns authenticator.core
  "easily create java.net.Authenticator instances see
  http://docs.oracle.com/javase/8/docs/api/java/net/Authenticator.html
  "
  (:import [java.net Authenticator PasswordAuthentication])
  (:require [authenticator.impl :refer [get-authenticator-options]]))

(defn- make-authenticator*
  [f]
  (proxy [authenticator.Authenticator] []
    (getPasswordAuthentication []
      (if-let [[username password] (-> this get-authenticator-options f)]
        (PasswordAuthentication. username (.toCharArray password))
        nil))))

(defn make-authenticator
  "create an Authenticator from the given function f. The function f
  will be called when the Authenticator's getPasswordAuthentication
  method is called. f is given a single map as argument with the following keys:

  :host            hostname of the site or proxy requesting authentication, or nil if not available
  :port            port number for the requested connection
  :prompt          prompt string given by the requestor
  :protocol        protocol for the requested connection
  :scheme          scheme for the requested connection
  :site            InetAddress of the site requesting authorization, or null if not available
  :url             URL that resulted in this request for authentication
  :requestor-type  :proxy for a Proxy or :server otherwise

  The function f should return a [username, password] vector or nil.
"
  [f]
  (cond
    (instance? java.net.Authenticator f)
      f
    (nil? f)
      nil
    :else
      (make-authenticator* f)))

(defn set-default-authenticator
  "Sets the authenticator that will be used by the networking code
  when a proxy or an HTTP server asks for authentication. You may pass
  in an Authenticator object or a function, which will then be
  converted to an Authenticator with make-authenticator"
  [a]
  (let [authenticator (make-authenticator a)]
    (Authenticator/setDefault authenticator)
    authenticator))
