(ns problems.93)

; flatten a list except lowest level

(defn flatish [s]
  (loop [v s r []]
    (if (empty? v)
      r
      (let [f (first v) f? (coll? (first f))]
        (recur
          (if f? (concat (first v) (rest v)) (rest v))
          (if f? r (conj r f)))))))

(def __ flatish)


(assert (= (__ [["Do"] ["Nothing"]])
           [["Do"] ["Nothing"]]))

(assert (= (__ [[[[:a :b]]] [[:c :d]] [:e :f]])
           [[:a :b] [:c :d] [:e :f]]))

(assert (= (__ '((1 2)((3 4)((((5 6)))))))
   '((1 2)(3 4)(5 6))))


; of course! all mapcat needed was an extra [] wrapper]

(fn f [xs]
  (if (every? sequential? xs) (mapcat f xs) [xs]))
