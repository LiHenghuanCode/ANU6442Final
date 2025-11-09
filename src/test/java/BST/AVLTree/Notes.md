题目原文的关键点是：

“All test cases should pass if an AVL tree implementation is correct.
At least one test case should fail if an AVL tree implementation is incorrect.”

现在的测试文件正好符合要求：

如果给定的实现（例如老师的正确 AVLTree）是平衡的 → 全部通过 ✅

如果给定的实现（比如 SampleAVLTree）只是普通 BST → 多个测试失败 ❌

所以：
这些 AssertionError 是预期结果，说明测试编写是正确的。
