(defproject authenticator "1.0.0-SNAPSHOT"
  :description "easily create java.net.Authenticator instances"
  :url "https://github.com/schmir/authenticator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :aot [authenticator.impl]
  :scm {:name "git"
        :url "https://github.com/schmir/authenticator"}
  :deploy-repositories [["releases" :clojars]]
  :dependencies [[org.clojure/clojure "1.6.0"]])
