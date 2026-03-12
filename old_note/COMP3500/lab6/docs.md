# Lab6

## Choosing a data structure

### Priority Queue

- findMatchOrNull():
    - Java internal PriorityQueue is not guaranteed to traverse the elements of the priority queue in any particular
      order. If you need ordered traversal, consider using Arrays.sort(pq.toArray()).
        - pq.toArray():
            - O(n)
        - sort():
            - O(nlogn)
    - Find player by iterating through sorted array.
        - O(n)
    - Total:
        - O(nlogn)

- getTop(), getBottom():
    - count == 1
        - constant time for the retrieval methods(peek)
            - O(1)
    - count > 1
        - same as `findMatchOrNull()`

- join(), leave():
    - Java internal PriorityQueue provides O(log(n)) time for the enqueuing and dequeuing methods (offer, poll, remove()
      and add).

### Sorted Array(list)

- findMatchOrNull():
    - Find player by binary search
        - O(logn)

- getTop(), getBottom():
    - O(count)

- join(), leave():
    - insertions and deletions need shifting elements to maintain the sorted order.
        - O(n)

### Binary Search Tree

- findMatchOrNull():
    - find player locate
        - O(logn)
    - additional local traversal
        - O(k)
    - total:
        - O(logn)

- getTop(), getBottom():
    - find min or max locate
        - O(logn)
    - additional local traversal
        - O(count)
    - total:
        - O(logn)

- join(), leave():
    - BST insertion, deletion
        - O(logn)

- Overall, the `BST` provides a balanced performance for all required operations
- Optimizing join() is crucial for performance.
    - Additionally, the League() constructor calls join() N times. Using a Binary Search Tree keeps join() at O(logN),
      resulting in an overall complexity of O(N log N).
    - In contrast, using a sorted array makes join() O(N) due to the cost of finding the insertion point and shifting
      elements, leading to an overall complexity of O(N²).

## findMatchOrNull()

- The key is to leverage following property.
- Finding child predecessor or successor is similar to deletion.

- Claim:
    - Let x be a node in a binary search tree that does not have a left child.
    - Let y be the lowest ancestor of x for which x is in the right subtree (i.e. the first time you travel upward and
      find that you are coming from a right child).
    - Then y is the in‑order predecessor of x ; that is, y has the largest key that is less than x’s key.

- Proof:

1. BST Invariant: In a binary search tree, for every node, all keys in its left subtree are less than the node’s key,
   and all keys in its right subtree are greater.
2. Case When x Has No Left Child:
   Since x has no left subtree, there is no candidate in x ’s left subtree that could be its predecessor. Therefore, the
   predecessor must be one of the ancestors of x .
3. Upward Traversal:
   Consider the path from x upward towards the root.
    - If x is a left child, then by the BST property, its parent has a key greater than x ’s key, so the parent cannot
      be x ’s predecessor.
    - Continue moving upward until you reach an ancestor y such that x is in the right subtree of y  (i.e. x is a right
      child of y ). At that point, y is the first ancestor encountered where y.key < x.key.
4. No Closer Candidate Exists:
   Suppose for contradiction that there exists another node z such that z is less than x and z is closer to x than y(
   i.e. z is greater than y but still less than x). By the BST properties, z must be in the right subtree of some
   ancestor of x. However, since y is the lowest ancestor for which x is a right child, no such z can exist between y
   and x in the in‑order sequence.
5. Conclusion:
   Therefore, y is the largest key less than x ’s key, making y the in‑order predecessor of x.

## getTop()

- Use reverse in-order traversal.
    - right sub -> node -> left sub
