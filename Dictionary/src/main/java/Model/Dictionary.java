package Model;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

/**
 * using dfs to organize the structure of the dictionary.
 * Each character of a word is a Node, A Node contains the data of a Word.
 */
public class Dictionary {
    private class Node {
        private Word word;
        private Node[] child = new Node[40];

        public Node() {
            for (int i = 0; i < 40; i++) {
                child[i] = null;
            }
        }

        public Word getWord() {
            return word;
        }

        public void setWord(Word word) {
            this.word = word;
        }

        public Node getChild(int i) {
            return child[i];
        }

        void setChild(int i, Node child) {
            this.child[i] = child;
        }

        /**
         * get the number of child of a Node
         *
         * @return the amount of node.
         */
        public int numberOfChild() {
            int cnt = 0;
            for (int i = 0; i < 40; i++) {
                if (child[i] != null) cnt++;
            }
            return cnt;
        }
    }

    private Node root;
    private int size;

    public Dictionary() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * find the position of a character in Node.
     *
     * @param character the character need to find.
     * @return the position.
     */
    private int getPos(char character) {
        if ('a' <= character && character <= 'z') return character - 'a' + 10;
        else if (character >= '0' && character <= '9') return character - '0';
        else if (character == ' ') return 36;
        else if (character == '-') return 37;
        else if (character == '\'') return 38;
        else return 39;
    }

    /**
     * dfs to the Node that
     *
     * @param word the word need to add.
     */
    public void add(Word word) {

        if (word.getWordTarget() == null || word.getWordTarget().equals("")) return;
//        if (find(word.getWordTarget()) != null) {
//            System.out.println(word.getWordTarget());
//            Node curr = find(word.getWordTarget());
//            String tmp = curr.getWord().getWordExplain();
//            tmp += "\n" + word.getWordExplain();
//            Word newWord = new Word(word.getWordTarget(), tmp);
//            curr.setWord(newWord);
//            return;
//        }
        String tmp = word.getWordTarget();
        Node curr = this.root;
        for (int i = 0; i < tmp.length(); i++) {
            int pos = getPos(tmp.charAt(i));
            if (curr.getChild(pos) != null) {
                curr = curr.getChild(pos);
            } else {
                Node newNode = new Node();
                curr.setChild(pos, newNode);
                curr = newNode;
            }
        }
        if (curr.getWord() == null) size++;
        curr.setWord(word);
    }

    public Node find(String target) {
        Node curr = this.root;
        for (int i = 0; i < target.length(); i++) {
            int pos = getPos(target.charAt(i));
            if (curr.getChild(pos) != null) {
                curr = curr.getChild(pos);
            } else return null;
        }
        return curr;
    }

    public Word erase_from_tree(String target) {
        Node curr = find(target);
        Word tmp = curr.getWord();
        curr.setWord(null);
        return tmp;
    }

    void getList(Node curr, List<Word> a) {
        if (curr == null) return;
        if (curr.getWord() != null) {
            a.add(curr.getWord());
        }
        for (int i = 0; i < 40; i++) getList(curr.getChild(i), a);
    }

    public List<Word> getWordList(String target) {
        Node tmp = find(target);
        List<Word> result = new ArrayList<>();
        getList(tmp, result);
        return result;
    }

    public String getInfo(Node target) {
        return target.getWord().getWordTarget() + " "
                + target.getWord().getWordExplain();
    }
    public String getWordVi(Node target) {
        return target.getWord().getWordExplain();
    }
    public String getWordEn(Node target) {
        return target.getWord().getWordTarget();
    }

}