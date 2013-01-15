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

(defn get-inbox-data [user-urn]
  (select [messages :c1]
          (modifier "distinct")
          (fields :topic_urn :sender_urn :receiver_urn 
           [(subselect messages
               (aggregate (count :*) :message_count)
               (where {:topic_urn :c1.topic_urn})) :message_count]
           [(subselect messages
               (aggregate (max :created_at) :last_update)
               (where {:topic_urn :c1.topic_urn})) :last_update]
           [(subselect messages
               (aggregate (max :created_at) :last_update)
               (where {:topic_urn :c1.topic_urn})) :user_urn])
          (where (or (= :receiver_urn user-urn)
                     (= :sender_urn user-urn)))))

(defn inbox [user-urn]
  (distinct (let [topics {}]
      (map (fn [row]
         (into topics {:topic_urn (row :topic_urn)
                          :message_count (row :message_count)
                          :last_update (row :last_update)
                          :user_urn (if (= user-urn (row :sender_urn))
                                      (row :receiver_urn)
                                      (row :sender_urn))})) (get-inbox-data user-urn)))))

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