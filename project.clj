(defproject msg-api-comp "0.1.0-SNAPSHOT"
  :description "Clojure experimental project. More info to come..."
  :url "https://github.com/podviaznikov/msg-api-comp"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [postgresql "9.1-901.jdbc4"]
  				 [korma "0.3.0-beta14"]
                 [compojure "1.1.5"]]
  :plugins [[lein-ring "0.7.1"]]
  :ring {:handler msg-api-comp.core/app})