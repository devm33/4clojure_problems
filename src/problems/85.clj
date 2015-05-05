(ns problems.85)

(defn __ [s]
  (if (empty? s)
    #{#{}}
    (let [sub (__ (rest s))]
      (apply conj sub (map #(conj % (first s)) sub)))))


(= (__ #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
