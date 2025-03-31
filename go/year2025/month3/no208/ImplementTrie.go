package main

func main() {
	trie := Constructor()
	//trie.Insert("apple")
	//println(trie.Search("apple"))   //true
	//println(trie.Search("app"))     //false
	//println(trie.StartsWith("app")) //true
	//trie.Insert("app")
	println(trie.Search("app")) //true
}

/**
 * Your Trie object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.Search(word);
 * param_3 := obj.StartsWith(prefix);
 */

type Trie struct {
	childs [26]*Trie // 只有小写字母，因此用长度为 26 的数组存储子节点
	word   string    // 用来存储单词，用于 Search 方法标记当前节点是否为一个单词（一个叶子节点）
}

func Constructor() Trie {
	return Trie{}
}

func (this *Trie) Insert(word string) {
	p := this
	for _, c := range word {
		index := c - 'a'
		if p.childs[index] == nil {
			p.childs[index] = &Trie{}
		}
		p = p.childs[index]
	}
	p.word = word
}

func (this *Trie) getByPrefix(prefix string) (res *Trie) {
	p := this
	for _, c := range prefix {
		index := c - 'a'
		if p.childs[index] == nil {
			return nil
		}
		p = p.childs[index]
	}
	return p

}

func (this *Trie) Search(word string) bool {
	p := this.getByPrefix(word)
	return p != nil && p.word == word
}

func (this *Trie) StartsWith(prefix string) bool {
	return this.getByPrefix(prefix) != nil
}

/*
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：
Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。


示例：
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True

提示：
1 <= word.length, prefix.length <= 2000
word 和 prefix 仅由小写英文字母组成
insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
*/
