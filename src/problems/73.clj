(ns problems.73)

(defn __ [rows]
  (let
    [checkxo (fn [pred] (if (pred :x) :x (if (pred :o) :o nil)))
     alleq (fn [c coll] (apply = c coll))
     hasgroupeq (fn [c groups] (some (partial alleq c) groups))
     cols (apply map vector rows)
     diag (map-indexed #(nth %2 %1) rows)
     bdiag (map-indexed #(nth %2 (- (dec (count (first rows))) %1)) rows)]
    (checkxo #(hasgroupeq % (concat rows cols [diag bdiag])))))


(assert (= nil (__ [[:e :e :e]
                    [:e :e :e]
                    [:e :e :e]])))

(assert (= :x (__ [[:x :e :o]
                   [:x :e :e]
                   [:x :e :o]])))

(assert (= :o (__ [[:e :x :e]
                   [:o :o :o]
                   [:x :e :x]])))

(assert (= nil (__ [[:x :e :o]
                    [:x :x :e]
                    [:o :x :o]])))

(assert (= :x (__ [[:x :e :e]
                   [:o :x :e]
                   [:o :e :x]])))

(assert (= :o (__ [[:x :e :o]
                   [:x :o :e]
                   [:o :e :x]])))

(assert (= nil (__ [[:x :o :x]
                    [:x :o :x]
                    [:o :x :o]])))
