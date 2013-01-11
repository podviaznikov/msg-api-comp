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

(defn inbox [user_urn]
	(select [messages :c1]
        (fields :topic_urn 
         	[(subselect messages
               (aggregate (count :*) :message_count)
               (where {:topic_urn :c1.topic_urn})) :message_count])))

(defn create [message]
  (insert messages 
    (values message)))

(defn -main []
  (print "Executing sql calls") (flush)
  (create {:topic_urn "msg-api-comp discusiion"
           :sender_urn "http://twitter.com/podviaznikov"
           :receiver_urn "http://twitter.com/m_aleksandrova"
           :message "Hi, can we discuss this project together?"})
  (create {:topic_urn "msg-api-comp discusiion"
           :sender_urn "http://twitter.com/m_aleksandrova"
           :receiver_urn "http://twitter.com/podviaznikov"
           :message "Yeah, sure. When do you want to do it?"})
  (create {:topic_urn "new year animation"
           :sender_urn "http://twitter.com/m_aleksandrova"
           :receiver_urn "http://twitter.com/anton"
           :message "We need to discuss New Year animation project"})
  (all-for-topic "msg-api-comp discusiion")

  (all-for-topic "msg-api-comp discusiion")
  (println " done..."))