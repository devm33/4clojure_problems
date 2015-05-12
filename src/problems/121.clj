(ns problems.121)

; no warning, but can't use eval

(defn __ [s]
  (let [fmap {'/ / '* * '+ + '- -}
        collf (fn [f x] (if (coll? x) (f x) x))]
    ((fn call [[o a b]] ((fmap o) (collf call a) (collf call b))) s)))


(assert (= 6 (__ '(+ 4 2))))

(assert (= 4 (__ '(+ (/ 4 2) 2))))



