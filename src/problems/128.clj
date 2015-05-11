(ns problems.128)

(defn __ [[s r]]
  {:suit (get {\D :diamond \H :heart \C :club \S :spade} s)
   :rank (if-let [R (get {\A 12 \K 11 \Q 10 \J 9 \T 8} r)] R
           (- (Character/getNumericValue r) 2))})

(__ "D8")
