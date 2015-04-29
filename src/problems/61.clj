(ns problems.61)

(= (

    #(reduce conj {} (map vector %1 %2))

    [:a :b :c]
    [1 2 3]
    )
   {:a 1 :b 2 :c 3})
