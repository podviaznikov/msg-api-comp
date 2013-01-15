(ns msg-api-comp.core
  (:use compojure.core)
  (:use cheshire.core)
  (:use msg-api-comp.services.msg-service)
  (:use ring.util.response)
  (:require [compojure.handler :as handler]
   			[compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defroutes app-routes
  (GET "/" [] "<h1>Hello World</h1>")
  (GET "/topics/:user-urn" [user-urn] 
       (generate-string
			(all-for-topic "msg-api-comp discusiion")))
  (route/not-found "<h1>Page not found</h1>"))

(defroutes app
  (-> (handler/api app-routes)))