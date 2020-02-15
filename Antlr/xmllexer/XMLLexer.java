// Generated from XMLLexer.g4 by ANTLR 4.8
package xmllexer;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XMLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HEADER=1, COMMENT=2, OPEN=3, EntityRef=4, TEXT=5, CLOSE=6, SLASH_CLOSE=7, 
		EQUALS=8, STRING=9, SlashName=10, Name=11, S=12;
	public static final int
		INSIDE=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "INSIDE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"HEADER", "COMMENT", "OPEN", "EntityRef", "TEXT", "CLOSE", "SLASH_CLOSE", 
			"EQUALS", "STRING", "SlashName", "Name", "S", "ALPHA", "DIGIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'<'", null, null, "'>'", "'/>'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "HEADER", "COMMENT", "OPEN", "EntityRef", "TEXT", "CLOSE", "SLASH_CLOSE", 
			"EQUALS", "STRING", "SlashName", "Name", "S"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public XMLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XMLLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16y\b\1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\7\2(\n\2\f\2\16\2+\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\67"+
		"\n\3\f\3\16\3:\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\6"+
		"\5H\n\5\r\5\16\5I\3\5\3\5\3\6\6\6O\n\6\r\6\16\6P\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\7\n`\n\n\f\n\16\nc\13\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\7\fm\n\f\f\f\16\fp\13\f\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\5)8a\2\20\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30"+
		"\r\32\16\34\2\36\2\4\2\3\7\3\2c|\4\2((>>\5\2\13\f\17\17\"\"\4\2C\\c|\3"+
		"\2\62;\2|\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2"+
		"\3\16\3\2\2\2\3\20\3\2\2\2\3\22\3\2\2\2\3\24\3\2\2\2\3\26\3\2\2\2\3\30"+
		"\3\2\2\2\3\32\3\2\2\2\4 \3\2\2\2\6\60\3\2\2\2\bA\3\2\2\2\nE\3\2\2\2\f"+
		"N\3\2\2\2\16R\3\2\2\2\20V\3\2\2\2\22[\3\2\2\2\24]\3\2\2\2\26f\3\2\2\2"+
		"\30i\3\2\2\2\32q\3\2\2\2\34u\3\2\2\2\36w\3\2\2\2 !\7>\2\2!\"\7A\2\2\""+
		"#\7z\2\2#$\7o\2\2$%\7n\2\2%)\3\2\2\2&(\13\2\2\2\'&\3\2\2\2(+\3\2\2\2)"+
		"*\3\2\2\2)\'\3\2\2\2*,\3\2\2\2+)\3\2\2\2,-\7@\2\2-.\3\2\2\2./\b\2\2\2"+
		"/\5\3\2\2\2\60\61\7>\2\2\61\62\7#\2\2\62\63\7/\2\2\63\64\7/\2\2\648\3"+
		"\2\2\2\65\67\13\2\2\2\66\65\3\2\2\2\67:\3\2\2\289\3\2\2\28\66\3\2\2\2"+
		"9;\3\2\2\2:8\3\2\2\2;<\7/\2\2<=\7/\2\2=>\7@\2\2>?\3\2\2\2?@\b\3\2\2@\7"+
		"\3\2\2\2AB\7>\2\2BC\3\2\2\2CD\b\4\3\2D\t\3\2\2\2EG\7(\2\2FH\t\2\2\2GF"+
		"\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\7=\2\2L\13\3\2\2\2"+
		"MO\n\3\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q\r\3\2\2\2RS\7@\2\2"+
		"ST\3\2\2\2TU\b\7\4\2U\17\3\2\2\2VW\7\61\2\2WX\7@\2\2XY\3\2\2\2YZ\b\b\4"+
		"\2Z\21\3\2\2\2[\\\7?\2\2\\\23\3\2\2\2]a\7$\2\2^`\13\2\2\2_^\3\2\2\2`c"+
		"\3\2\2\2ab\3\2\2\2a_\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\7$\2\2e\25\3\2\2\2"+
		"fg\7\61\2\2gh\5\30\f\2h\27\3\2\2\2in\5\34\16\2jm\5\34\16\2km\5\36\17\2"+
		"lj\3\2\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\31\3\2\2\2pn\3\2\2"+
		"\2qr\t\4\2\2rs\3\2\2\2st\b\r\2\2t\33\3\2\2\2uv\t\5\2\2v\35\3\2\2\2wx\t"+
		"\6\2\2x\37\3\2\2\2\13\2\3)8IPaln\5\b\2\2\7\3\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}