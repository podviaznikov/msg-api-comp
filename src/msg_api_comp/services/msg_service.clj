(ns msg-api-comp.services.msg-service)

(use 'korma.db)

(use 'korma.core)

(defdb prod (postgres 
             {:db "podviaznikov"
              :user "podviaznikov"
              :password ""}))

(defentity messages)

(defn -main []
  (print "Executing sql call") (flush)
  (select messages)
  (println " done"))