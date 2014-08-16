(ns authenticator.impl
  ""
  (:import [java.net Authenticator PasswordAuthentication Authenticator$RequestorType]))

(def requestor-type->keyword
  {Authenticator$RequestorType/PROXY :proxy
   Authenticator$RequestorType/SERVER :server})


(gen-class
 :name authenticator.Authenticator
 :extends java.net.Authenticator
 :exposes-methods {getRequestingHost _host
                   getRequestingPort _port
                   getRequestingPrompt _prompt
                   getRequestingProtocol _protocol
                   getRequestingScheme _scheme
                   getRequestingSite _site
                   getRequestingURL _url
                   getRequestorType _type})

(defn get-authenticator-options
  [this]
  {:host (._host this)
   :port (._port this)
   :prompt (._prompt this)
   :protocol (._protocol this)
   :scheme (._scheme this)
   :site (._site this)
   :url (._url this)
   :type (requestor-type->keyword (._type this))})
