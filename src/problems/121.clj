(ns problems.121)

; no warning, but can't use eval or resolve

(defn __ [s]
  (let [fmap {'/ / '* * '+ + '- -}]
    (fn [v]
      ((fn call [[o & a]]
         (apply (fmap o) (map #(if (coll? %) (call %) (get v % %)) a))) s))))

(assert (= 2 ((__ '(/ a b))
              '{b 8 a 16})))

(assert (= 8 ((__ '(+ a b 2)) '{a 2 b 4})))

(assert (= [6 0 -4]
           (map (__ '(* (+ 2 a)
                        (- 10 b)))
                '[{a 1 b 8}
                  {b 5 a -2}
                  {a 2 b 11}])))

(assert (= 1 ((__ '(/ (+ x 2)
                      (* 3 (+ y 1))))
              '{x 4 y 1})))
