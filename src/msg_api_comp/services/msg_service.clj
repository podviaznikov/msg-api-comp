(ns msg-api-comp.services.msg-service)

(use 'korma.db)

(use 'korma.core)

(defdb prod (postgres 
             {:db "podviaznikov"
              :user "podviaznikov"
              :password ""}))

(defentity messages)

(defn all-for-topic [topic_urn]
  (select messages
    (where {:topic_urn topic_urn})))

(defn create [message]
  (insert messages 
    (values message)))

(defn -main []
  (print "Executing sql calls") (flush)
  (create {:topic_urn "msg-api-comp discusiion"
           :sender_urn "http://twitter.com/podviaznikov"
           :receiver_urn "http://twitter.com/m_aleksandrova"
           :message "Hi, can we discuss this project together?"})
  (println " done..."))