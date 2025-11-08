package ParsingPractice.q5Parsing;

/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 *
 * Question:
 * Write a parse to recognize the language specified by the following grammar:
 * S := () ; S := (S) ; S := SS ;
 */
public class Parser {
	Tokeniser _tokeniser;

    public Parser(Tokeniser tokeniser) {
    	_tokeniser = tokeniser;
    }

    /**
	 * @return True if the given tokens conform with the grammar. False, otherwise.
	 */
	public boolean parseExp() {
		// 空字符串不符合语法
		if (!_tokeniser.hasNext()) {
			return false;
		}
		// 解析 S
		if (!parseS()) {
			return false;
		}
		// 确保所有token都被消耗完
		return parseS()&&!_tokeniser.hasNext() && _tokeniser.isNextValid();
	}
	private boolean parseS() {
		// 1. S 必须以 '(' 开头
		Token leftParen = _tokeniser.next();
		if (leftParen == null || leftParen.type != Token.Type.LEFT_BRACKET) {
			return false;
		}
		_tokeniser.takeNext(); // 消耗 '('

		// 2. 查看下一个token
		Token nextToken = _tokeniser.next();
		if (nextToken == null) {
			return false;
		}
		if (nextToken.type == Token.Type.RIGHT_BRACKET) {
			// 情况1: S := ()
			_tokeniser.takeNext(); // 消耗 ')'

		} else if (nextToken.type == Token.Type.LEFT_BRACKET) {
			// 情况2: S := (S)
			// 递归解析内部的 S
			if (!parseS()) {
				return false;
			}
			// 必须有对应的 ')'
			Token rightParen = _tokeniser.next();
			if (rightParen == null || rightParen.type != Token.Type.RIGHT_BRACKET) {
				return false;
			}
			_tokeniser.takeNext(); // 消耗 ')'
		} else {
			return false;
		}


		// 3. 检查是否有 SS（第二个S）
		Token lookAhead = _tokeniser.next();
		if (lookAhead != null && lookAhead.type == Token.Type.LEFT_BRACKET) {
			// 有另一个S，递归解析
			return parseS();
		}
		// 成功解析一个S
		return true;
	}
}
