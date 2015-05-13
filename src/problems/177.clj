(ns problems.177)

(defn __ [code]
  (let [co {\} \{ \] \[ \) \(} open (set (vals co))
        bracket (apply conj open (set (keys co)))]
    (loop [xs (filter bracket (seq code)) stack '()]
      (if (empty? xs) (empty? stack)
        (let [x (first xs)
              nstack (if (open x) (conj stack x)
                       (if (= (first stack) (co x)) (rest stack) nil))]
          (if (nil? nstack) false (recur (rest xs) nstack)))))))



(assert (__ "(hello)"))
(assert (not (__ ")(hello)")))
(assert (not (__ "(start, end]")))
