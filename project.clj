(defproject authenticator "0.1.1-SNAPSHOT"
  :description "easily create java.net.Authenticator instances"
  :url "https://github.com/schmir/authenticator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :aot [authenticator.impl]
  :scm {:name "git"
        :url "https://github.com/schmir/authenticator.git"}
  :dependencies [[org.clojure/clojure "1.6.0"]])
