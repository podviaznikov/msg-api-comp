(ns msg-api-comp.migrations.tables
  (:require [clojure.java.jdbc :as sql]))

(defn create-messages-table []
  (sql/with-connection "postgres://podviaznikov@localhost/podviaznikov"
    (sql/create-table :messages
      [:id :serial "PRIMARY KEY"]
      [:sender_urn :varchar "NOT NULL"]
      [:receiver_urn :varchar "NOT NULL"]
      [:topic_urn :varchar "NOT NULL"]
      [:message :text "NOT NULL"]	
      [:updated_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]
      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn -main []
  (print "Creating database structure...") (flush)
  (create-messages-table)
  (println " done"))