(ns msg-api-comp.core
  (:use compojure.core)
  (:use cheshire.core)
  (:use ring.util.response)
  (:require [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defroutes app
  (GET "/" [] "<h1>Hello World</h1>")
  (GET "/topics/:user-urn" [user-urn] (str "Hello"))
  (route/not-found "<h1>Page not found</h1>"))