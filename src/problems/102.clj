(ns problems.102)


(def __
  #(loop [s (seq %) c false r []]
     (if (empty? s)
       (apply str r)
       (let [f (first s) nc (= \- f)]
         (recur (rest s) nc (conj r (if nc "" (if c (Character/toUpperCase f) f)))))))
  )


(__ "something-test")
