// Generated from SlideDeckML.g4 by ANTLR 4.13.1
package slidedeckml.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SlideDeckMLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		T__80=81, T__81=82, T__82=83, T__83=84, T__84=85, T__85=86, T__86=87, 
		T__87=88, T__88=89, T__89=90, T__90=91, T__91=92, T__92=93, T__93=94, 
		T__94=95, T__95=96, T__96=97, T__97=98, T__98=99, T__99=100, T__100=101, 
		T__101=102, T__102=103, T__103=104, T__104=105, MLSTR=106, STR=107, FLOAT=108, 
		INT=109, ID=110, LINE_COMMENT=111, BLOCK_COMMENT=112, WS=113, BOM=114;
	public static final int
		RULE_file = 0, RULE_deckDecl = 1, RULE_deckItem = 2, RULE_headDecl = 3, 
		RULE_headProp = 4, RULE_templateDecl = 5, RULE_templateProp = 6, RULE_layoutDecl = 7, 
		RULE_layoutKind = 8, RULE_layoutZone = 9, RULE_area = 10, RULE_slideDecl = 11, 
		RULE_slideFrontProp = 12, RULE_transitionValue = 13, RULE_transitionObjProp = 14, 
		RULE_slideBodyItem = 15, RULE_slotDecl = 16, RULE_markdownDecl = 17, RULE_elementDecl = 18, 
		RULE_elementType = 19, RULE_elementProp = 20, RULE_positionDecl = 21, 
		RULE_absolutePosition = 22, RULE_relativePosition = 23, RULE_animationDecl = 24, 
		RULE_animationKind = 25, RULE_unit = 26, RULE_anchorType = 27, RULE_align = 28, 
		RULE_imageFit = 29, RULE_equationDisplayMode = 30, RULE_stepDecl = 31, 
		RULE_stepItem = 32, RULE_range = 33, RULE_valueList = 34, RULE_value = 35, 
		RULE_array = 36, RULE_obj = 37, RULE_number = 38, RULE_bool = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "deckDecl", "deckItem", "headDecl", "headProp", "templateDecl", 
			"templateProp", "layoutDecl", "layoutKind", "layoutZone", "area", "slideDecl", 
			"slideFrontProp", "transitionValue", "transitionObjProp", "slideBodyItem", 
			"slotDecl", "markdownDecl", "elementDecl", "elementType", "elementProp", 
			"positionDecl", "absolutePosition", "relativePosition", "animationDecl", 
			"animationKind", "unit", "anchorType", "align", "imageFit", "equationDisplayMode", 
			"stepDecl", "stepItem", "range", "valueList", "value", "array", "obj", 
			"number", "bool"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'deck'", "'{'", "'}'", "'head'", "'theme'", "';'", "'title'", 
			"'author'", "'exportFilename'", "'mdc'", "'defaults'", "'template'", 
			"'cssPath'", "'assetsDir'", "'logoPaths'", "'['", "']'", "'layout'", 
			"'kind'", "'zones'", "'STRUCTURED'", "'FLEX'", "'FREE'", "'zone'", "'role'", 
			"'defaultOrder'", "'HEADER'", "'CONTENT'", "'LEFT'", "'RIGHT'", "'FOOTER'", 
			"'CANVAS'", "'slide'", "'class'", "'transition'", "'notes'", "'|'", "':'", 
			"'slot'", "'markdown'", "'element'", "'type'", "'TextBlock'", "'ListBlock'", 
			"'ImageElement'", "'VideoElement'", "'CodeBlock'", "'EquationBlock'", 
			"'content'", "'ordered'", "'src'", "'altText'", "'fit'", "'autoplay'", 
			"'loop'", "'muted'", "'controls'", "'language'", "'displayMode'", "'latexSource'", 
			"'zIndex'", "'slotOf'", "'position'", "'absolute'", "'x'", "'y'", "'width'", 
			"'height'", "'unit'", "'anchor'", "'relative'", "'alignX'", "'alignY'", 
			"'orderInZone'", "'inZone'", "'animation'", "'delayMs'", "'durationMs'", 
			"'APPEAR'", "'FADE_IN'", "'FADE_IN_SLIDE_IN'", "'ZOOM_IN'", "'PERCENT'", 
			"'PX'", "'TOP_LEFT'", "'TOP_CENTER'", "'CENTER'", "'BOTTOM_CENTER'", 
			"'BOTTOM_RIGHT'", "'START'", "'END'", "'CONTAIN'", "'COVER'", "'STRETCH'", 
			"'INLINE'", "'BLOCK'", "'step'", "'reveal'", "'codeReveal'", "'switch'", 
			"'show'", "'..'", "','", "'true'", "'false'", null, null, null, null, 
			null, null, null, null, "'\\uFEFF'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "MLSTR", 
			"STR", "FLOAT", "INT", "ID", "LINE_COMMENT", "BLOCK_COMMENT", "WS", "BOM"
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

	@Override
	public String getGrammarFileName() { return "SlideDeckML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SlideDeckMLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public DeckDeclContext deckDecl() {
			return getRuleContext(DeckDeclContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SlideDeckMLParser.EOF, 0); }
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			deckDecl();
			setState(81);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeckDeclContext extends ParserRuleContext {
		public Token deckName;
		public TerminalNode STR() { return getToken(SlideDeckMLParser.STR, 0); }
		public List<DeckItemContext> deckItem() {
			return getRuleContexts(DeckItemContext.class);
		}
		public DeckItemContext deckItem(int i) {
			return getRuleContext(DeckItemContext.class,i);
		}
		public DeckDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deckDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitDeckDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeckDeclContext deckDecl() throws RecognitionException {
		DeckDeclContext _localctx = new DeckDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_deckDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__0);
			setState(84);
			((DeckDeclContext)_localctx).deckName = match(STR);
			setState(85);
			match(T__1);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8590200848L) != 0)) {
				{
				{
				setState(86);
				deckItem();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeckItemContext extends ParserRuleContext {
		public HeadDeclContext headDecl() {
			return getRuleContext(HeadDeclContext.class,0);
		}
		public TemplateDeclContext templateDecl() {
			return getRuleContext(TemplateDeclContext.class,0);
		}
		public LayoutDeclContext layoutDecl() {
			return getRuleContext(LayoutDeclContext.class,0);
		}
		public SlideDeclContext slideDecl() {
			return getRuleContext(SlideDeclContext.class,0);
		}
		public DeckItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deckItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitDeckItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeckItemContext deckItem() throws RecognitionException {
		DeckItemContext _localctx = new DeckItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_deckItem);
		try {
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				headDecl();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				templateDecl();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				layoutDecl();
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 4);
				{
				setState(97);
				slideDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeadDeclContext extends ParserRuleContext {
		public List<HeadPropContext> headProp() {
			return getRuleContexts(HeadPropContext.class);
		}
		public HeadPropContext headProp(int i) {
			return getRuleContext(HeadPropContext.class,i);
		}
		public HeadDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitHeadDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadDeclContext headDecl() throws RecognitionException {
		HeadDeclContext _localctx = new HeadDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_headDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__3);
			setState(101);
			match(T__1);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4000L) != 0)) {
				{
				{
				setState(102);
				headProp();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeadPropContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public List<SlideFrontPropContext> slideFrontProp() {
			return getRuleContexts(SlideFrontPropContext.class);
		}
		public SlideFrontPropContext slideFrontProp(int i) {
			return getRuleContext(SlideFrontPropContext.class,i);
		}
		public HeadPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headProp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitHeadProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadPropContext headProp() throws RecognitionException {
		HeadPropContext _localctx = new HeadPropContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_headProp);
		int _la;
		try {
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(T__4);
				setState(111);
				value();
				setState(112);
				match(T__5);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(T__6);
				setState(115);
				value();
				setState(116);
				match(T__5);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				match(T__7);
				setState(119);
				value();
				setState(120);
				match(T__5);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(122);
				match(T__8);
				setState(123);
				value();
				setState(124);
				match(T__5);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				match(T__9);
				setState(127);
				value();
				setState(128);
				match(T__5);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(130);
				match(T__10);
				setState(131);
				match(T__1);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 120259346560L) != 0)) {
					{
					{
					setState(132);
					slideFrontProp();
					}
					}
					setState(137);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(138);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TemplateDeclContext extends ParserRuleContext {
		public Token templateName;
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public List<TemplatePropContext> templateProp() {
			return getRuleContexts(TemplatePropContext.class);
		}
		public TemplatePropContext templateProp(int i) {
			return getRuleContext(TemplatePropContext.class,i);
		}
		public TemplateDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitTemplateDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateDeclContext templateDecl() throws RecognitionException {
		TemplateDeclContext _localctx = new TemplateDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_templateDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__11);
			setState(142);
			((TemplateDeclContext)_localctx).templateName = match(ID);
			setState(143);
			match(T__1);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) {
				{
				{
				setState(144);
				templateProp();
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(150);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TemplatePropContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ValueListContext valueList() {
			return getRuleContext(ValueListContext.class,0);
		}
		public TemplatePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateProp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitTemplateProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplatePropContext templateProp() throws RecognitionException {
		TemplatePropContext _localctx = new TemplatePropContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_templateProp);
		int _la;
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(T__12);
				setState(153);
				value();
				setState(154);
				match(T__5);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				match(T__13);
				setState(157);
				value();
				setState(158);
				match(T__5);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				match(T__14);
				setState(161);
				match(T__15);
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1 || _la==T__15 || ((((_la - 104)) & ~0x3f) == 0 && ((1L << (_la - 104)) & 127L) != 0)) {
					{
					setState(162);
					valueList();
					}
				}

				setState(165);
				match(T__16);
				setState(166);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LayoutDeclContext extends ParserRuleContext {
		public Token layoutName;
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public LayoutKindContext layoutKind() {
			return getRuleContext(LayoutKindContext.class,0);
		}
		public List<LayoutZoneContext> layoutZone() {
			return getRuleContexts(LayoutZoneContext.class);
		}
		public LayoutZoneContext layoutZone(int i) {
			return getRuleContext(LayoutZoneContext.class,i);
		}
		public LayoutDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_layoutDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitLayoutDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LayoutDeclContext layoutDecl() throws RecognitionException {
		LayoutDeclContext _localctx = new LayoutDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_layoutDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__17);
			setState(170);
			((LayoutDeclContext)_localctx).layoutName = match(ID);
			setState(171);
			match(T__1);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(172);
				match(T__18);
				setState(173);
				layoutKind();
				setState(174);
				match(T__5);
				}
			}

			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(178);
				match(T__19);
				setState(179);
				match(T__1);
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__23) {
					{
					{
					setState(180);
					layoutZone();
					}
					}
					setState(185);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(186);
				match(T__2);
				}
			}

			setState(189);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LayoutKindContext extends ParserRuleContext {
		public LayoutKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_layoutKind; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitLayoutKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LayoutKindContext layoutKind() throws RecognitionException {
		LayoutKindContext _localctx = new LayoutKindContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_layoutKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LayoutZoneContext extends ParserRuleContext {
		public Token zoneName;
		public AreaContext area() {
			return getRuleContext(AreaContext.class,0);
		}
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public TerminalNode INT() { return getToken(SlideDeckMLParser.INT, 0); }
		public LayoutZoneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_layoutZone; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitLayoutZone(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LayoutZoneContext layoutZone() throws RecognitionException {
		LayoutZoneContext _localctx = new LayoutZoneContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_layoutZone);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(T__23);
			setState(194);
			((LayoutZoneContext)_localctx).zoneName = match(ID);
			setState(195);
			match(T__1);
			setState(196);
			match(T__24);
			setState(197);
			area();
			setState(198);
			match(T__5);
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__25) {
				{
				setState(199);
				match(T__25);
				setState(200);
				match(INT);
				setState(201);
				match(T__5);
				}
			}

			setState(204);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AreaContext extends ParserRuleContext {
		public AreaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_area; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitArea(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AreaContext area() throws RecognitionException {
		AreaContext _localctx = new AreaContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_area);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8455716864L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SlideDeclContext extends ParserRuleContext {
		public Token slideId;
		public List<SlideFrontPropContext> slideFrontProp() {
			return getRuleContexts(SlideFrontPropContext.class);
		}
		public SlideFrontPropContext slideFrontProp(int i) {
			return getRuleContext(SlideFrontPropContext.class,i);
		}
		public List<SlideBodyItemContext> slideBodyItem() {
			return getRuleContexts(SlideBodyItemContext.class);
		}
		public SlideBodyItemContext slideBodyItem(int i) {
			return getRuleContext(SlideBodyItemContext.class,i);
		}
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public SlideDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slideDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitSlideDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SlideDeclContext slideDecl() throws RecognitionException {
		SlideDeclContext _localctx = new SlideDeclContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_slideDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__32);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(209);
				((SlideDeclContext)_localctx).slideId = match(ID);
				}
			}

			setState(212);
			match(T__1);
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 120259346560L) != 0)) {
				{
				{
				setState(213);
				slideFrontProp();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & 288230376151711751L) != 0)) {
				{
				{
				setState(219);
				slideBodyItem();
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(225);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SlideFrontPropContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public TransitionValueContext transitionValue() {
			return getRuleContext(TransitionValueContext.class,0);
		}
		public SlideFrontPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slideFrontProp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitSlideFrontProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SlideFrontPropContext slideFrontProp() throws RecognitionException {
		SlideFrontPropContext _localctx = new SlideFrontPropContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_slideFrontProp);
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				match(T__6);
				setState(228);
				value();
				setState(229);
				match(T__5);
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				match(T__17);
				setState(232);
				match(ID);
				setState(233);
				match(T__5);
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
				match(T__33);
				setState(235);
				value();
				setState(236);
				match(T__5);
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 4);
				{
				setState(238);
				match(T__34);
				setState(239);
				transitionValue();
				setState(240);
				match(T__5);
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 5);
				{
				setState(242);
				match(T__35);
				setState(243);
				value();
				setState(244);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TransitionValueContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SlideDeckMLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SlideDeckMLParser.ID, i);
		}
		public List<TransitionObjPropContext> transitionObjProp() {
			return getRuleContexts(TransitionObjPropContext.class);
		}
		public TransitionObjPropContext transitionObjProp(int i) {
			return getRuleContext(TransitionObjPropContext.class,i);
		}
		public TransitionValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitTransitionValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionValueContext transitionValue() throws RecognitionException {
		TransitionValueContext _localctx = new TransitionValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_transitionValue);
		int _la;
		try {
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				match(ID);
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__36) {
					{
					setState(249);
					match(T__36);
					setState(250);
					match(ID);
					}
				}

				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(253);
				match(T__1);
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					{
					setState(254);
					transitionObjProp();
					}
					}
					setState(259);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(260);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TransitionObjPropContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TransitionObjPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionObjProp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitTransitionObjProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionObjPropContext transitionObjProp() throws RecognitionException {
		TransitionObjPropContext _localctx = new TransitionObjPropContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_transitionObjProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(ID);
			setState(264);
			match(T__37);
			setState(265);
			value();
			setState(266);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SlideBodyItemContext extends ParserRuleContext {
		public SlotDeclContext slotDecl() {
			return getRuleContext(SlotDeclContext.class,0);
		}
		public ElementDeclContext elementDecl() {
			return getRuleContext(ElementDeclContext.class,0);
		}
		public StepDeclContext stepDecl() {
			return getRuleContext(StepDeclContext.class,0);
		}
		public MarkdownDeclContext markdownDecl() {
			return getRuleContext(MarkdownDeclContext.class,0);
		}
		public SlideBodyItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slideBodyItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitSlideBodyItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SlideBodyItemContext slideBodyItem() throws RecognitionException {
		SlideBodyItemContext _localctx = new SlideBodyItemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_slideBodyItem);
		try {
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				slotDecl();
				}
				break;
			case T__40:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				elementDecl();
				}
				break;
			case T__96:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				stepDecl();
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 4);
				{
				setState(271);
				markdownDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SlotDeclContext extends ParserRuleContext {
		public Token slotName;
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public SlotDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slotDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitSlotDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SlotDeclContext slotDecl() throws RecognitionException {
		SlotDeclContext _localctx = new SlotDeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_slotDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(T__38);
			setState(275);
			((SlotDeclContext)_localctx).slotName = match(ID);
			setState(276);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MarkdownDeclContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public MarkdownDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_markdownDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitMarkdownDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MarkdownDeclContext markdownDecl() throws RecognitionException {
		MarkdownDeclContext _localctx = new MarkdownDeclContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_markdownDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(T__39);
			setState(279);
			value();
			setState(280);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementDeclContext extends ParserRuleContext {
		public Token elementId;
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public ElementTypeContext elementType() {
			return getRuleContext(ElementTypeContext.class,0);
		}
		public List<ElementPropContext> elementProp() {
			return getRuleContexts(ElementPropContext.class);
		}
		public ElementPropContext elementProp(int i) {
			return getRuleContext(ElementPropContext.class,i);
		}
		public ElementDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitElementDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementDeclContext elementDecl() throws RecognitionException {
		ElementDeclContext _localctx = new ElementDeclContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_elementDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__40);
			setState(283);
			((ElementDeclContext)_localctx).elementId = match(ID);
			setState(284);
			match(T__1);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__41) {
				{
				setState(285);
				match(T__41);
				setState(286);
				elementType();
				setState(287);
				match(T__5);
				}
			}

			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 49)) & ~0x3f) == 0 && ((1L << (_la - 49)) & 134250495L) != 0)) {
				{
				{
				setState(291);
				elementProp();
				}
				}
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(297);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementTypeContext extends ParserRuleContext {
		public ElementTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitElementType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementTypeContext elementType() throws RecognitionException {
		ElementTypeContext _localctx = new ElementTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_elementType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementPropContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ImageFitContext imageFit() {
			return getRuleContext(ImageFitContext.class,0);
		}
		public EquationDisplayModeContext equationDisplayMode() {
			return getRuleContext(EquationDisplayModeContext.class,0);
		}
		public TerminalNode INT() { return getToken(SlideDeckMLParser.INT, 0); }
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public PositionDeclContext positionDecl() {
			return getRuleContext(PositionDeclContext.class,0);
		}
		public AnimationDeclContext animationDecl() {
			return getRuleContext(AnimationDeclContext.class,0);
		}
		public ElementPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementProp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitElementProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementPropContext elementProp() throws RecognitionException {
		ElementPropContext _localctx = new ElementPropContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_elementProp);
		try {
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__48:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				match(T__48);
				setState(302);
				value();
				setState(303);
				match(T__5);
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				match(T__49);
				setState(306);
				value();
				setState(307);
				match(T__5);
				}
				break;
			case T__50:
				enterOuterAlt(_localctx, 3);
				{
				setState(309);
				match(T__50);
				setState(310);
				value();
				setState(311);
				match(T__5);
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 4);
				{
				setState(313);
				match(T__51);
				setState(314);
				value();
				setState(315);
				match(T__5);
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 5);
				{
				setState(317);
				match(T__52);
				setState(318);
				imageFit();
				setState(319);
				match(T__5);
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 6);
				{
				setState(321);
				match(T__53);
				setState(322);
				value();
				setState(323);
				match(T__5);
				}
				break;
			case T__54:
				enterOuterAlt(_localctx, 7);
				{
				setState(325);
				match(T__54);
				setState(326);
				value();
				setState(327);
				match(T__5);
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 8);
				{
				setState(329);
				match(T__55);
				setState(330);
				value();
				setState(331);
				match(T__5);
				}
				break;
			case T__56:
				enterOuterAlt(_localctx, 9);
				{
				setState(333);
				match(T__56);
				setState(334);
				value();
				setState(335);
				match(T__5);
				}
				break;
			case T__57:
				enterOuterAlt(_localctx, 10);
				{
				setState(337);
				match(T__57);
				setState(338);
				value();
				setState(339);
				match(T__5);
				}
				break;
			case T__58:
				enterOuterAlt(_localctx, 11);
				{
				setState(341);
				match(T__58);
				setState(342);
				equationDisplayMode();
				setState(343);
				match(T__5);
				}
				break;
			case T__59:
				enterOuterAlt(_localctx, 12);
				{
				setState(345);
				match(T__59);
				setState(346);
				value();
				setState(347);
				match(T__5);
				}
				break;
			case T__60:
				enterOuterAlt(_localctx, 13);
				{
				setState(349);
				match(T__60);
				setState(350);
				match(INT);
				setState(351);
				match(T__5);
				}
				break;
			case T__61:
				enterOuterAlt(_localctx, 14);
				{
				setState(352);
				match(T__61);
				setState(353);
				match(ID);
				setState(354);
				match(T__5);
				}
				break;
			case T__62:
				enterOuterAlt(_localctx, 15);
				{
				setState(355);
				positionDecl();
				}
				break;
			case T__75:
				enterOuterAlt(_localctx, 16);
				{
				setState(356);
				animationDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PositionDeclContext extends ParserRuleContext {
		public AbsolutePositionContext absolutePosition() {
			return getRuleContext(AbsolutePositionContext.class,0);
		}
		public RelativePositionContext relativePosition() {
			return getRuleContext(RelativePositionContext.class,0);
		}
		public PositionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positionDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitPositionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositionDeclContext positionDecl() throws RecognitionException {
		PositionDeclContext _localctx = new PositionDeclContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_positionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(T__62);
			setState(362);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__63:
				{
				setState(360);
				absolutePosition();
				}
				break;
			case T__70:
				{
				setState(361);
				relativePosition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AbsolutePositionContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public AnchorTypeContext anchorType() {
			return getRuleContext(AnchorTypeContext.class,0);
		}
		public AbsolutePositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absolutePosition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitAbsolutePosition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbsolutePositionContext absolutePosition() throws RecognitionException {
		AbsolutePositionContext _localctx = new AbsolutePositionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_absolutePosition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(T__63);
			setState(365);
			match(T__1);
			setState(366);
			match(T__64);
			setState(367);
			number();
			setState(368);
			match(T__5);
			setState(369);
			match(T__65);
			setState(370);
			number();
			setState(371);
			match(T__5);
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__66) {
				{
				setState(372);
				match(T__66);
				setState(373);
				number();
				setState(374);
				match(T__5);
				}
			}

			setState(382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__67) {
				{
				setState(378);
				match(T__67);
				setState(379);
				number();
				setState(380);
				match(T__5);
				}
			}

			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__68) {
				{
				setState(384);
				match(T__68);
				setState(385);
				unit();
				setState(386);
				match(T__5);
				}
			}

			setState(394);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__69) {
				{
				setState(390);
				match(T__69);
				setState(391);
				anchorType();
				setState(392);
				match(T__5);
				}
			}

			setState(396);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelativePositionContext extends ParserRuleContext {
		public List<AlignContext> align() {
			return getRuleContexts(AlignContext.class);
		}
		public AlignContext align(int i) {
			return getRuleContext(AlignContext.class,i);
		}
		public TerminalNode INT() { return getToken(SlideDeckMLParser.INT, 0); }
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public RelativePositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relativePosition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitRelativePosition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelativePositionContext relativePosition() throws RecognitionException {
		RelativePositionContext _localctx = new RelativePositionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_relativePosition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(T__70);
			setState(399);
			match(T__1);
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__71) {
				{
				setState(400);
				match(T__71);
				setState(401);
				align();
				setState(402);
				match(T__5);
				}
			}

			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__72) {
				{
				setState(406);
				match(T__72);
				setState(407);
				align();
				setState(408);
				match(T__5);
				}
			}

			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__73) {
				{
				setState(412);
				match(T__73);
				setState(413);
				match(INT);
				setState(414);
				match(T__5);
				}
			}

			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__74) {
				{
				setState(417);
				match(T__74);
				setState(418);
				match(ID);
				setState(419);
				match(T__5);
				}
			}

			setState(422);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnimationDeclContext extends ParserRuleContext {
		public AnimationKindContext animationKind() {
			return getRuleContext(AnimationKindContext.class,0);
		}
		public List<TerminalNode> INT() { return getTokens(SlideDeckMLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SlideDeckMLParser.INT, i);
		}
		public AnimationDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_animationDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitAnimationDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnimationDeclContext animationDecl() throws RecognitionException {
		AnimationDeclContext _localctx = new AnimationDeclContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_animationDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			match(T__75);
			setState(425);
			match(T__1);
			setState(426);
			match(T__18);
			setState(427);
			animationKind();
			setState(428);
			match(T__5);
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__76) {
				{
				setState(429);
				match(T__76);
				setState(430);
				match(INT);
				setState(431);
				match(T__5);
				}
			}

			setState(437);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__77) {
				{
				setState(434);
				match(T__77);
				setState(435);
				match(INT);
				setState(436);
				match(T__5);
				}
			}

			setState(439);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnimationKindContext extends ParserRuleContext {
		public AnimationKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_animationKind; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitAnimationKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnimationKindContext animationKind() throws RecognitionException {
		AnimationKindContext _localctx = new AnimationKindContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_animationKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			_la = _input.LA(1);
			if ( !(((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnitContext extends ParserRuleContext {
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_unit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			_la = _input.LA(1);
			if ( !(_la==T__82 || _la==T__83) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnchorTypeContext extends ParserRuleContext {
		public AnchorTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anchorType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitAnchorType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnchorTypeContext anchorType() throws RecognitionException {
		AnchorTypeContext _localctx = new AnchorTypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_anchorType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_la = _input.LA(1);
			if ( !(((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 31L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlignContext extends ParserRuleContext {
		public AlignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_align; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitAlign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlignContext align() throws RecognitionException {
		AlignContext _localctx = new AlignContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_align);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			_la = _input.LA(1);
			if ( !(((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & 25L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImageFitContext extends ParserRuleContext {
		public ImageFitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imageFit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitImageFit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImageFitContext imageFit() throws RecognitionException {
		ImageFitContext _localctx = new ImageFitContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_imageFit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			_la = _input.LA(1);
			if ( !(((((_la - 92)) & ~0x3f) == 0 && ((1L << (_la - 92)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EquationDisplayModeContext extends ParserRuleContext {
		public EquationDisplayModeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equationDisplayMode; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitEquationDisplayMode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationDisplayModeContext equationDisplayMode() throws RecognitionException {
		EquationDisplayModeContext _localctx = new EquationDisplayModeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_equationDisplayMode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			_la = _input.LA(1);
			if ( !(_la==T__94 || _la==T__95) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StepDeclContext extends ParserRuleContext {
		public Token stepIndex;
		public TerminalNode INT() { return getToken(SlideDeckMLParser.INT, 0); }
		public List<StepItemContext> stepItem() {
			return getRuleContexts(StepItemContext.class);
		}
		public StepItemContext stepItem(int i) {
			return getRuleContext(StepItemContext.class,i);
		}
		public StepDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitStepDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepDeclContext stepDecl() throws RecognitionException {
		StepDeclContext _localctx = new StepDeclContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_stepDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			match(T__96);
			setState(454);
			((StepDeclContext)_localctx).stepIndex = match(INT);
			setState(455);
			match(T__1);
			setState(459);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 98)) & ~0x3f) == 0 && ((1L << (_la - 98)) & 7L) != 0)) {
				{
				{
				setState(456);
				stepItem();
				}
				}
				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(462);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StepItemContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SlideDeckMLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SlideDeckMLParser.ID, i);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public StepItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitStepItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepItemContext stepItem() throws RecognitionException {
		StepItemContext _localctx = new StepItemContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_stepItem);
		try {
			setState(477);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__97:
				enterOuterAlt(_localctx, 1);
				{
				setState(464);
				match(T__97);
				setState(465);
				match(ID);
				setState(466);
				match(T__5);
				}
				break;
			case T__98:
				enterOuterAlt(_localctx, 2);
				{
				setState(467);
				match(T__98);
				setState(468);
				match(ID);
				setState(469);
				range();
				setState(470);
				match(T__5);
				}
				break;
			case T__99:
				enterOuterAlt(_localctx, 3);
				{
				setState(472);
				match(T__99);
				setState(473);
				match(ID);
				setState(474);
				match(T__100);
				setState(475);
				match(ID);
				setState(476);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RangeContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(SlideDeckMLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SlideDeckMLParser.INT, i);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			match(INT);
			setState(480);
			match(T__101);
			setState(481);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueListContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitValueList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueListContext valueList() throws RecognitionException {
		ValueListContext _localctx = new ValueListContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_valueList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			value();
			setState(488);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__102) {
				{
				{
				setState(484);
				match(T__102);
				setState(485);
				value();
				}
				}
				setState(490);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STR() { return getToken(SlideDeckMLParser.STR, 0); }
		public TerminalNode MLSTR() { return getToken(SlideDeckMLParser.MLSTR, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public TerminalNode ID() { return getToken(SlideDeckMLParser.ID, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_value);
		try {
			setState(498);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STR:
				enterOuterAlt(_localctx, 1);
				{
				setState(491);
				match(STR);
				}
				break;
			case MLSTR:
				enterOuterAlt(_localctx, 2);
				{
				setState(492);
				match(MLSTR);
				}
				break;
			case FLOAT:
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(493);
				number();
				}
				break;
			case T__103:
			case T__104:
				enterOuterAlt(_localctx, 4);
				{
				setState(494);
				bool();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(495);
				match(ID);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 6);
				{
				setState(496);
				array();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 7);
				{
				setState(497);
				obj();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			match(T__15);
			setState(509);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==T__15 || ((((_la - 104)) & ~0x3f) == 0 && ((1L << (_la - 104)) & 127L) != 0)) {
				{
				setState(501);
				value();
				setState(506);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__102) {
					{
					{
					setState(502);
					match(T__102);
					setState(503);
					value();
					}
					}
					setState(508);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(511);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObjContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SlideDeckMLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SlideDeckMLParser.ID, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitObj(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_obj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(T__1);
			setState(521);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(514);
				match(ID);
				setState(515);
				match(T__37);
				setState(516);
				value();
				setState(517);
				match(T__5);
				}
				}
				setState(523);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(524);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(SlideDeckMLParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(SlideDeckMLParser.INT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(526);
			_la = _input.LA(1);
			if ( !(_la==FLOAT || _la==INT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolContext extends ParserRuleContext {
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SlideDeckMLVisitor ) return ((SlideDeckMLVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			_la = _input.LA(1);
			if ( !(_la==T__103 || _la==T__104) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001r\u0213\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001X\b\u0001\n\u0001\f\u0001[\t\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002c\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003h\b\u0003\n\u0003"+
		"\f\u0003k\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u0086\b\u0004\n\u0004\f\u0004"+
		"\u0089\t\u0004\u0001\u0004\u0003\u0004\u008c\b\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u0092\b\u0005\n\u0005\f\u0005"+
		"\u0095\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\u00a4\b\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006\u00a8\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00b1\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007\u00b6\b\u0007\n\u0007\f\u0007\u00b9"+
		"\t\u0007\u0001\u0007\u0003\u0007\u00bc\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u00cb\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00d3\b\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u00d7\b\u000b\n\u000b\f\u000b\u00da\t\u000b\u0001\u000b\u0005\u000b"+
		"\u00dd\b\u000b\n\u000b\f\u000b\u00e0\t\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00f7\b\f\u0001\r\u0001\r\u0001\r\u0003\r\u00fc\b\r\u0001\r"+
		"\u0001\r\u0005\r\u0100\b\r\n\r\f\r\u0103\t\r\u0001\r\u0003\r\u0106\b\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0111\b\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u0122\b\u0012\u0001\u0012\u0005\u0012"+
		"\u0125\b\u0012\n\u0012\f\u0012\u0128\t\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0166\b\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u016b\b\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0179"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u017f"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0185"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u018b"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0195\b\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u019b\b\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u01a0\b\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u01a5\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u01b1\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u01b6\b\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0005\u001f\u01ca\b\u001f\n\u001f\f\u001f\u01cd\t\u001f"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u01de\b \u0001!\u0001"+
		"!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0005\"\u01e7\b\"\n\"\f\"\u01ea"+
		"\t\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003#\u01f3\b#"+
		"\u0001$\u0001$\u0001$\u0001$\u0005$\u01f9\b$\n$\f$\u01fc\t$\u0003$\u01fe"+
		"\b$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0005%\u0208"+
		"\b%\n%\f%\u020b\t%\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001\'"+
		"\u0000\u0000(\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLN\u0000\u000b\u0001\u0000"+
		"\u0015\u0017\u0001\u0000\u001b \u0001\u0000+0\u0001\u0000OR\u0001\u0000"+
		"ST\u0001\u0000UY\u0002\u0000WWZ[\u0001\u0000\\^\u0001\u0000_`\u0001\u0000"+
		"lm\u0001\u0000hi\u0233\u0000P\u0001\u0000\u0000\u0000\u0002S\u0001\u0000"+
		"\u0000\u0000\u0004b\u0001\u0000\u0000\u0000\u0006d\u0001\u0000\u0000\u0000"+
		"\b\u008b\u0001\u0000\u0000\u0000\n\u008d\u0001\u0000\u0000\u0000\f\u00a7"+
		"\u0001\u0000\u0000\u0000\u000e\u00a9\u0001\u0000\u0000\u0000\u0010\u00bf"+
		"\u0001\u0000\u0000\u0000\u0012\u00c1\u0001\u0000\u0000\u0000\u0014\u00ce"+
		"\u0001\u0000\u0000\u0000\u0016\u00d0\u0001\u0000\u0000\u0000\u0018\u00f6"+
		"\u0001\u0000\u0000\u0000\u001a\u0105\u0001\u0000\u0000\u0000\u001c\u0107"+
		"\u0001\u0000\u0000\u0000\u001e\u0110\u0001\u0000\u0000\u0000 \u0112\u0001"+
		"\u0000\u0000\u0000\"\u0116\u0001\u0000\u0000\u0000$\u011a\u0001\u0000"+
		"\u0000\u0000&\u012b\u0001\u0000\u0000\u0000(\u0165\u0001\u0000\u0000\u0000"+
		"*\u0167\u0001\u0000\u0000\u0000,\u016c\u0001\u0000\u0000\u0000.\u018e"+
		"\u0001\u0000\u0000\u00000\u01a8\u0001\u0000\u0000\u00002\u01b9\u0001\u0000"+
		"\u0000\u00004\u01bb\u0001\u0000\u0000\u00006\u01bd\u0001\u0000\u0000\u0000"+
		"8\u01bf\u0001\u0000\u0000\u0000:\u01c1\u0001\u0000\u0000\u0000<\u01c3"+
		"\u0001\u0000\u0000\u0000>\u01c5\u0001\u0000\u0000\u0000@\u01dd\u0001\u0000"+
		"\u0000\u0000B\u01df\u0001\u0000\u0000\u0000D\u01e3\u0001\u0000\u0000\u0000"+
		"F\u01f2\u0001\u0000\u0000\u0000H\u01f4\u0001\u0000\u0000\u0000J\u0201"+
		"\u0001\u0000\u0000\u0000L\u020e\u0001\u0000\u0000\u0000N\u0210\u0001\u0000"+
		"\u0000\u0000PQ\u0003\u0002\u0001\u0000QR\u0005\u0000\u0000\u0001R\u0001"+
		"\u0001\u0000\u0000\u0000ST\u0005\u0001\u0000\u0000TU\u0005k\u0000\u0000"+
		"UY\u0005\u0002\u0000\u0000VX\u0003\u0004\u0002\u0000WV\u0001\u0000\u0000"+
		"\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000YZ\u0001\u0000"+
		"\u0000\u0000Z\\\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000\\]\u0005"+
		"\u0003\u0000\u0000]\u0003\u0001\u0000\u0000\u0000^c\u0003\u0006\u0003"+
		"\u0000_c\u0003\n\u0005\u0000`c\u0003\u000e\u0007\u0000ac\u0003\u0016\u000b"+
		"\u0000b^\u0001\u0000\u0000\u0000b_\u0001\u0000\u0000\u0000b`\u0001\u0000"+
		"\u0000\u0000ba\u0001\u0000\u0000\u0000c\u0005\u0001\u0000\u0000\u0000"+
		"de\u0005\u0004\u0000\u0000ei\u0005\u0002\u0000\u0000fh\u0003\b\u0004\u0000"+
		"gf\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000"+
		"\u0000ij\u0001\u0000\u0000\u0000jl\u0001\u0000\u0000\u0000ki\u0001\u0000"+
		"\u0000\u0000lm\u0005\u0003\u0000\u0000m\u0007\u0001\u0000\u0000\u0000"+
		"no\u0005\u0005\u0000\u0000op\u0003F#\u0000pq\u0005\u0006\u0000\u0000q"+
		"\u008c\u0001\u0000\u0000\u0000rs\u0005\u0007\u0000\u0000st\u0003F#\u0000"+
		"tu\u0005\u0006\u0000\u0000u\u008c\u0001\u0000\u0000\u0000vw\u0005\b\u0000"+
		"\u0000wx\u0003F#\u0000xy\u0005\u0006\u0000\u0000y\u008c\u0001\u0000\u0000"+
		"\u0000z{\u0005\t\u0000\u0000{|\u0003F#\u0000|}\u0005\u0006\u0000\u0000"+
		"}\u008c\u0001\u0000\u0000\u0000~\u007f\u0005\n\u0000\u0000\u007f\u0080"+
		"\u0003F#\u0000\u0080\u0081\u0005\u0006\u0000\u0000\u0081\u008c\u0001\u0000"+
		"\u0000\u0000\u0082\u0083\u0005\u000b\u0000\u0000\u0083\u0087\u0005\u0002"+
		"\u0000\u0000\u0084\u0086\u0003\u0018\f\u0000\u0085\u0084\u0001\u0000\u0000"+
		"\u0000\u0086\u0089\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008a\u0001\u0000\u0000"+
		"\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u008a\u008c\u0005\u0003\u0000"+
		"\u0000\u008bn\u0001\u0000\u0000\u0000\u008br\u0001\u0000\u0000\u0000\u008b"+
		"v\u0001\u0000\u0000\u0000\u008bz\u0001\u0000\u0000\u0000\u008b~\u0001"+
		"\u0000\u0000\u0000\u008b\u0082\u0001\u0000\u0000\u0000\u008c\t\u0001\u0000"+
		"\u0000\u0000\u008d\u008e\u0005\f\u0000\u0000\u008e\u008f\u0005n\u0000"+
		"\u0000\u008f\u0093\u0005\u0002\u0000\u0000\u0090\u0092\u0003\f\u0006\u0000"+
		"\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u0095\u0001\u0000\u0000\u0000"+
		"\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000"+
		"\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000"+
		"\u0096\u0097\u0005\u0003\u0000\u0000\u0097\u000b\u0001\u0000\u0000\u0000"+
		"\u0098\u0099\u0005\r\u0000\u0000\u0099\u009a\u0003F#\u0000\u009a\u009b"+
		"\u0005\u0006\u0000\u0000\u009b\u00a8\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0005\u000e\u0000\u0000\u009d\u009e\u0003F#\u0000\u009e\u009f\u0005\u0006"+
		"\u0000\u0000\u009f\u00a8\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005\u000f"+
		"\u0000\u0000\u00a1\u00a3\u0005\u0010\u0000\u0000\u00a2\u00a4\u0003D\""+
		"\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005\u0011\u0000"+
		"\u0000\u00a6\u00a8\u0005\u0006\u0000\u0000\u00a7\u0098\u0001\u0000\u0000"+
		"\u0000\u00a7\u009c\u0001\u0000\u0000\u0000\u00a7\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a8\r\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005\u0012\u0000\u0000"+
		"\u00aa\u00ab\u0005n\u0000\u0000\u00ab\u00b0\u0005\u0002\u0000\u0000\u00ac"+
		"\u00ad\u0005\u0013\u0000\u0000\u00ad\u00ae\u0003\u0010\b\u0000\u00ae\u00af"+
		"\u0005\u0006\u0000\u0000\u00af\u00b1\u0001\u0000\u0000\u0000\u00b0\u00ac"+
		"\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1\u00bb"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u0014\u0000\u0000\u00b3\u00b7"+
		"\u0005\u0002\u0000\u0000\u00b4\u00b6\u0003\u0012\t\u0000\u00b5\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00ba\u0001"+
		"\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bc\u0005"+
		"\u0003\u0000\u0000\u00bb\u00b2\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001"+
		"\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00be\u0005"+
		"\u0003\u0000\u0000\u00be\u000f\u0001\u0000\u0000\u0000\u00bf\u00c0\u0007"+
		"\u0000\u0000\u0000\u00c0\u0011\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005"+
		"\u0018\u0000\u0000\u00c2\u00c3\u0005n\u0000\u0000\u00c3\u00c4\u0005\u0002"+
		"\u0000\u0000\u00c4\u00c5\u0005\u0019\u0000\u0000\u00c5\u00c6\u0003\u0014"+
		"\n\u0000\u00c6\u00ca\u0005\u0006\u0000\u0000\u00c7\u00c8\u0005\u001a\u0000"+
		"\u0000\u00c8\u00c9\u0005m\u0000\u0000\u00c9\u00cb\u0005\u0006\u0000\u0000"+
		"\u00ca\u00c7\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005\u0003\u0000\u0000"+
		"\u00cd\u0013\u0001\u0000\u0000\u0000\u00ce\u00cf\u0007\u0001\u0000\u0000"+
		"\u00cf\u0015\u0001\u0000\u0000\u0000\u00d0\u00d2\u0005!\u0000\u0000\u00d1"+
		"\u00d3\u0005n\u0000\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d2\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d8"+
		"\u0005\u0002\u0000\u0000\u00d5\u00d7\u0003\u0018\f\u0000\u00d6\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d7\u00da\u0001\u0000\u0000\u0000\u00d8\u00d6\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00de\u0001"+
		"\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000\u00db\u00dd\u0003"+
		"\u001e\u000f\u0000\u00dc\u00db\u0001\u0000\u0000\u0000\u00dd\u00e0\u0001"+
		"\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00de\u00df\u0001"+
		"\u0000\u0000\u0000\u00df\u00e1\u0001\u0000\u0000\u0000\u00e0\u00de\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e2\u0005\u0003\u0000\u0000\u00e2\u0017\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e4\u0005\u0007\u0000\u0000\u00e4\u00e5\u0003"+
		"F#\u0000\u00e5\u00e6\u0005\u0006\u0000\u0000\u00e6\u00f7\u0001\u0000\u0000"+
		"\u0000\u00e7\u00e8\u0005\u0012\u0000\u0000\u00e8\u00e9\u0005n\u0000\u0000"+
		"\u00e9\u00f7\u0005\u0006\u0000\u0000\u00ea\u00eb\u0005\"\u0000\u0000\u00eb"+
		"\u00ec\u0003F#\u0000\u00ec\u00ed\u0005\u0006\u0000\u0000\u00ed\u00f7\u0001"+
		"\u0000\u0000\u0000\u00ee\u00ef\u0005#\u0000\u0000\u00ef\u00f0\u0003\u001a"+
		"\r\u0000\u00f0\u00f1\u0005\u0006\u0000\u0000\u00f1\u00f7\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f3\u0005$\u0000\u0000\u00f3\u00f4\u0003F#\u0000\u00f4"+
		"\u00f5\u0005\u0006\u0000\u0000\u00f5\u00f7\u0001\u0000\u0000\u0000\u00f6"+
		"\u00e3\u0001\u0000\u0000\u0000\u00f6\u00e7\u0001\u0000\u0000\u0000\u00f6"+
		"\u00ea\u0001\u0000\u0000\u0000\u00f6\u00ee\u0001\u0000\u0000\u0000\u00f6"+
		"\u00f2\u0001\u0000\u0000\u0000\u00f7\u0019\u0001\u0000\u0000\u0000\u00f8"+
		"\u00fb\u0005n\u0000\u0000\u00f9\u00fa\u0005%\u0000\u0000\u00fa\u00fc\u0005"+
		"n\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000"+
		"\u0000\u0000\u00fc\u0106\u0001\u0000\u0000\u0000\u00fd\u0101\u0005\u0002"+
		"\u0000\u0000\u00fe\u0100\u0003\u001c\u000e\u0000\u00ff\u00fe\u0001\u0000"+
		"\u0000\u0000\u0100\u0103\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000"+
		"\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u0104\u0001\u0000"+
		"\u0000\u0000\u0103\u0101\u0001\u0000\u0000\u0000\u0104\u0106\u0005\u0003"+
		"\u0000\u0000\u0105\u00f8\u0001\u0000\u0000\u0000\u0105\u00fd\u0001\u0000"+
		"\u0000\u0000\u0106\u001b\u0001\u0000\u0000\u0000\u0107\u0108\u0005n\u0000"+
		"\u0000\u0108\u0109\u0005&\u0000\u0000\u0109\u010a\u0003F#\u0000\u010a"+
		"\u010b\u0005\u0006\u0000\u0000\u010b\u001d\u0001\u0000\u0000\u0000\u010c"+
		"\u0111\u0003 \u0010\u0000\u010d\u0111\u0003$\u0012\u0000\u010e\u0111\u0003"+
		">\u001f\u0000\u010f\u0111\u0003\"\u0011\u0000\u0110\u010c\u0001\u0000"+
		"\u0000\u0000\u0110\u010d\u0001\u0000\u0000\u0000\u0110\u010e\u0001\u0000"+
		"\u0000\u0000\u0110\u010f\u0001\u0000\u0000\u0000\u0111\u001f\u0001\u0000"+
		"\u0000\u0000\u0112\u0113\u0005\'\u0000\u0000\u0113\u0114\u0005n\u0000"+
		"\u0000\u0114\u0115\u0005\u0006\u0000\u0000\u0115!\u0001\u0000\u0000\u0000"+
		"\u0116\u0117\u0005(\u0000\u0000\u0117\u0118\u0003F#\u0000\u0118\u0119"+
		"\u0005\u0006\u0000\u0000\u0119#\u0001\u0000\u0000\u0000\u011a\u011b\u0005"+
		")\u0000\u0000\u011b\u011c\u0005n\u0000\u0000\u011c\u0121\u0005\u0002\u0000"+
		"\u0000\u011d\u011e\u0005*\u0000\u0000\u011e\u011f\u0003&\u0013\u0000\u011f"+
		"\u0120\u0005\u0006\u0000\u0000\u0120\u0122\u0001\u0000\u0000\u0000\u0121"+
		"\u011d\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122"+
		"\u0126\u0001\u0000\u0000\u0000\u0123\u0125\u0003(\u0014\u0000\u0124\u0123"+
		"\u0001\u0000\u0000\u0000\u0125\u0128\u0001\u0000\u0000\u0000\u0126\u0124"+
		"\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0129"+
		"\u0001\u0000\u0000\u0000\u0128\u0126\u0001\u0000\u0000\u0000\u0129\u012a"+
		"\u0005\u0003\u0000\u0000\u012a%\u0001\u0000\u0000\u0000\u012b\u012c\u0007"+
		"\u0002\u0000\u0000\u012c\'\u0001\u0000\u0000\u0000\u012d\u012e\u00051"+
		"\u0000\u0000\u012e\u012f\u0003F#\u0000\u012f\u0130\u0005\u0006\u0000\u0000"+
		"\u0130\u0166\u0001\u0000\u0000\u0000\u0131\u0132\u00052\u0000\u0000\u0132"+
		"\u0133\u0003F#\u0000\u0133\u0134\u0005\u0006\u0000\u0000\u0134\u0166\u0001"+
		"\u0000\u0000\u0000\u0135\u0136\u00053\u0000\u0000\u0136\u0137\u0003F#"+
		"\u0000\u0137\u0138\u0005\u0006\u0000\u0000\u0138\u0166\u0001\u0000\u0000"+
		"\u0000\u0139\u013a\u00054\u0000\u0000\u013a\u013b\u0003F#\u0000\u013b"+
		"\u013c\u0005\u0006\u0000\u0000\u013c\u0166\u0001\u0000\u0000\u0000\u013d"+
		"\u013e\u00055\u0000\u0000\u013e\u013f\u0003:\u001d\u0000\u013f\u0140\u0005"+
		"\u0006\u0000\u0000\u0140\u0166\u0001\u0000\u0000\u0000\u0141\u0142\u0005"+
		"6\u0000\u0000\u0142\u0143\u0003F#\u0000\u0143\u0144\u0005\u0006\u0000"+
		"\u0000\u0144\u0166\u0001\u0000\u0000\u0000\u0145\u0146\u00057\u0000\u0000"+
		"\u0146\u0147\u0003F#\u0000\u0147\u0148\u0005\u0006\u0000\u0000\u0148\u0166"+
		"\u0001\u0000\u0000\u0000\u0149\u014a\u00058\u0000\u0000\u014a\u014b\u0003"+
		"F#\u0000\u014b\u014c\u0005\u0006\u0000\u0000\u014c\u0166\u0001\u0000\u0000"+
		"\u0000\u014d\u014e\u00059\u0000\u0000\u014e\u014f\u0003F#\u0000\u014f"+
		"\u0150\u0005\u0006\u0000\u0000\u0150\u0166\u0001\u0000\u0000\u0000\u0151"+
		"\u0152\u0005:\u0000\u0000\u0152\u0153\u0003F#\u0000\u0153\u0154\u0005"+
		"\u0006\u0000\u0000\u0154\u0166\u0001\u0000\u0000\u0000\u0155\u0156\u0005"+
		";\u0000\u0000\u0156\u0157\u0003<\u001e\u0000\u0157\u0158\u0005\u0006\u0000"+
		"\u0000\u0158\u0166\u0001\u0000\u0000\u0000\u0159\u015a\u0005<\u0000\u0000"+
		"\u015a\u015b\u0003F#\u0000\u015b\u015c\u0005\u0006\u0000\u0000\u015c\u0166"+
		"\u0001\u0000\u0000\u0000\u015d\u015e\u0005=\u0000\u0000\u015e\u015f\u0005"+
		"m\u0000\u0000\u015f\u0166\u0005\u0006\u0000\u0000\u0160\u0161\u0005>\u0000"+
		"\u0000\u0161\u0162\u0005n\u0000\u0000\u0162\u0166\u0005\u0006\u0000\u0000"+
		"\u0163\u0166\u0003*\u0015\u0000\u0164\u0166\u00030\u0018\u0000\u0165\u012d"+
		"\u0001\u0000\u0000\u0000\u0165\u0131\u0001\u0000\u0000\u0000\u0165\u0135"+
		"\u0001\u0000\u0000\u0000\u0165\u0139\u0001\u0000\u0000\u0000\u0165\u013d"+
		"\u0001\u0000\u0000\u0000\u0165\u0141\u0001\u0000\u0000\u0000\u0165\u0145"+
		"\u0001\u0000\u0000\u0000\u0165\u0149\u0001\u0000\u0000\u0000\u0165\u014d"+
		"\u0001\u0000\u0000\u0000\u0165\u0151\u0001\u0000\u0000\u0000\u0165\u0155"+
		"\u0001\u0000\u0000\u0000\u0165\u0159\u0001\u0000\u0000\u0000\u0165\u015d"+
		"\u0001\u0000\u0000\u0000\u0165\u0160\u0001\u0000\u0000\u0000\u0165\u0163"+
		"\u0001\u0000\u0000\u0000\u0165\u0164\u0001\u0000\u0000\u0000\u0166)\u0001"+
		"\u0000\u0000\u0000\u0167\u016a\u0005?\u0000\u0000\u0168\u016b\u0003,\u0016"+
		"\u0000\u0169\u016b\u0003.\u0017\u0000\u016a\u0168\u0001\u0000\u0000\u0000"+
		"\u016a\u0169\u0001\u0000\u0000\u0000\u016b+\u0001\u0000\u0000\u0000\u016c"+
		"\u016d\u0005@\u0000\u0000\u016d\u016e\u0005\u0002\u0000\u0000\u016e\u016f"+
		"\u0005A\u0000\u0000\u016f\u0170\u0003L&\u0000\u0170\u0171\u0005\u0006"+
		"\u0000\u0000\u0171\u0172\u0005B\u0000\u0000\u0172\u0173\u0003L&\u0000"+
		"\u0173\u0178\u0005\u0006\u0000\u0000\u0174\u0175\u0005C\u0000\u0000\u0175"+
		"\u0176\u0003L&\u0000\u0176\u0177\u0005\u0006\u0000\u0000\u0177\u0179\u0001"+
		"\u0000\u0000\u0000\u0178\u0174\u0001\u0000\u0000\u0000\u0178\u0179\u0001"+
		"\u0000\u0000\u0000\u0179\u017e\u0001\u0000\u0000\u0000\u017a\u017b\u0005"+
		"D\u0000\u0000\u017b\u017c\u0003L&\u0000\u017c\u017d\u0005\u0006\u0000"+
		"\u0000\u017d\u017f\u0001\u0000\u0000\u0000\u017e\u017a\u0001\u0000\u0000"+
		"\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0184\u0001\u0000\u0000"+
		"\u0000\u0180\u0181\u0005E\u0000\u0000\u0181\u0182\u00034\u001a\u0000\u0182"+
		"\u0183\u0005\u0006\u0000\u0000\u0183\u0185\u0001\u0000\u0000\u0000\u0184"+
		"\u0180\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000\u0000\u0000\u0185"+
		"\u018a\u0001\u0000\u0000\u0000\u0186\u0187\u0005F\u0000\u0000\u0187\u0188"+
		"\u00036\u001b\u0000\u0188\u0189\u0005\u0006\u0000\u0000\u0189\u018b\u0001"+
		"\u0000\u0000\u0000\u018a\u0186\u0001\u0000\u0000\u0000\u018a\u018b\u0001"+
		"\u0000\u0000\u0000\u018b\u018c\u0001\u0000\u0000\u0000\u018c\u018d\u0005"+
		"\u0003\u0000\u0000\u018d-\u0001\u0000\u0000\u0000\u018e\u018f\u0005G\u0000"+
		"\u0000\u018f\u0194\u0005\u0002\u0000\u0000\u0190\u0191\u0005H\u0000\u0000"+
		"\u0191\u0192\u00038\u001c\u0000\u0192\u0193\u0005\u0006\u0000\u0000\u0193"+
		"\u0195\u0001\u0000\u0000\u0000\u0194\u0190\u0001\u0000\u0000\u0000\u0194"+
		"\u0195\u0001\u0000\u0000\u0000\u0195\u019a\u0001\u0000\u0000\u0000\u0196"+
		"\u0197\u0005I\u0000\u0000\u0197\u0198\u00038\u001c\u0000\u0198\u0199\u0005"+
		"\u0006\u0000\u0000\u0199\u019b\u0001\u0000\u0000\u0000\u019a\u0196\u0001"+
		"\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u019f\u0001"+
		"\u0000\u0000\u0000\u019c\u019d\u0005J\u0000\u0000\u019d\u019e\u0005m\u0000"+
		"\u0000\u019e\u01a0\u0005\u0006\u0000\u0000\u019f\u019c\u0001\u0000\u0000"+
		"\u0000\u019f\u01a0\u0001\u0000\u0000\u0000\u01a0\u01a4\u0001\u0000\u0000"+
		"\u0000\u01a1\u01a2\u0005K\u0000\u0000\u01a2\u01a3\u0005n\u0000\u0000\u01a3"+
		"\u01a5\u0005\u0006\u0000\u0000\u01a4\u01a1\u0001\u0000\u0000\u0000\u01a4"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000\u0000\u0000\u01a6"+
		"\u01a7\u0005\u0003\u0000\u0000\u01a7/\u0001\u0000\u0000\u0000\u01a8\u01a9"+
		"\u0005L\u0000\u0000\u01a9\u01aa\u0005\u0002\u0000\u0000\u01aa\u01ab\u0005"+
		"\u0013\u0000\u0000\u01ab\u01ac\u00032\u0019\u0000\u01ac\u01b0\u0005\u0006"+
		"\u0000\u0000\u01ad\u01ae\u0005M\u0000\u0000\u01ae\u01af\u0005m\u0000\u0000"+
		"\u01af\u01b1\u0005\u0006\u0000\u0000\u01b0\u01ad\u0001\u0000\u0000\u0000"+
		"\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b2\u01b3\u0005N\u0000\u0000\u01b3\u01b4\u0005m\u0000\u0000\u01b4\u01b6"+
		"\u0005\u0006\u0000\u0000\u01b5\u01b2\u0001\u0000\u0000\u0000\u01b5\u01b6"+
		"\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8"+
		"\u0005\u0003\u0000\u0000\u01b81\u0001\u0000\u0000\u0000\u01b9\u01ba\u0007"+
		"\u0003\u0000\u0000\u01ba3\u0001\u0000\u0000\u0000\u01bb\u01bc\u0007\u0004"+
		"\u0000\u0000\u01bc5\u0001\u0000\u0000\u0000\u01bd\u01be\u0007\u0005\u0000"+
		"\u0000\u01be7\u0001\u0000\u0000\u0000\u01bf\u01c0\u0007\u0006\u0000\u0000"+
		"\u01c09\u0001\u0000\u0000\u0000\u01c1\u01c2\u0007\u0007\u0000\u0000\u01c2"+
		";\u0001\u0000\u0000\u0000\u01c3\u01c4\u0007\b\u0000\u0000\u01c4=\u0001"+
		"\u0000\u0000\u0000\u01c5\u01c6\u0005a\u0000\u0000\u01c6\u01c7\u0005m\u0000"+
		"\u0000\u01c7\u01cb\u0005\u0002\u0000\u0000\u01c8\u01ca\u0003@ \u0000\u01c9"+
		"\u01c8\u0001\u0000\u0000\u0000\u01ca\u01cd\u0001\u0000\u0000\u0000\u01cb"+
		"\u01c9\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc"+
		"\u01ce\u0001\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000\u0000\u0000\u01ce"+
		"\u01cf\u0005\u0003\u0000\u0000\u01cf?\u0001\u0000\u0000\u0000\u01d0\u01d1"+
		"\u0005b\u0000\u0000\u01d1\u01d2\u0005n\u0000\u0000\u01d2\u01de\u0005\u0006"+
		"\u0000\u0000\u01d3\u01d4\u0005c\u0000\u0000\u01d4\u01d5\u0005n\u0000\u0000"+
		"\u01d5\u01d6\u0003B!\u0000\u01d6\u01d7\u0005\u0006\u0000\u0000\u01d7\u01de"+
		"\u0001\u0000\u0000\u0000\u01d8\u01d9\u0005d\u0000\u0000\u01d9\u01da\u0005"+
		"n\u0000\u0000\u01da\u01db\u0005e\u0000\u0000\u01db\u01dc\u0005n\u0000"+
		"\u0000\u01dc\u01de\u0005\u0006\u0000\u0000\u01dd\u01d0\u0001\u0000\u0000"+
		"\u0000\u01dd\u01d3\u0001\u0000\u0000\u0000\u01dd\u01d8\u0001\u0000\u0000"+
		"\u0000\u01deA\u0001\u0000\u0000\u0000\u01df\u01e0\u0005m\u0000\u0000\u01e0"+
		"\u01e1\u0005f\u0000\u0000\u01e1\u01e2\u0005m\u0000\u0000\u01e2C\u0001"+
		"\u0000\u0000\u0000\u01e3\u01e8\u0003F#\u0000\u01e4\u01e5\u0005g\u0000"+
		"\u0000\u01e5\u01e7\u0003F#\u0000\u01e6\u01e4\u0001\u0000\u0000\u0000\u01e7"+
		"\u01ea\u0001\u0000\u0000\u0000\u01e8\u01e6\u0001\u0000\u0000\u0000\u01e8"+
		"\u01e9\u0001\u0000\u0000\u0000\u01e9E\u0001\u0000\u0000\u0000\u01ea\u01e8"+
		"\u0001\u0000\u0000\u0000\u01eb\u01f3\u0005k\u0000\u0000\u01ec\u01f3\u0005"+
		"j\u0000\u0000\u01ed\u01f3\u0003L&\u0000\u01ee\u01f3\u0003N\'\u0000\u01ef"+
		"\u01f3\u0005n\u0000\u0000\u01f0\u01f3\u0003H$\u0000\u01f1\u01f3\u0003"+
		"J%\u0000\u01f2\u01eb\u0001\u0000\u0000\u0000\u01f2\u01ec\u0001\u0000\u0000"+
		"\u0000\u01f2\u01ed\u0001\u0000\u0000\u0000\u01f2\u01ee\u0001\u0000\u0000"+
		"\u0000\u01f2\u01ef\u0001\u0000\u0000\u0000\u01f2\u01f0\u0001\u0000\u0000"+
		"\u0000\u01f2\u01f1\u0001\u0000\u0000\u0000\u01f3G\u0001\u0000\u0000\u0000"+
		"\u01f4\u01fd\u0005\u0010\u0000\u0000\u01f5\u01fa\u0003F#\u0000\u01f6\u01f7"+
		"\u0005g\u0000\u0000\u01f7\u01f9\u0003F#\u0000\u01f8\u01f6\u0001\u0000"+
		"\u0000\u0000\u01f9\u01fc\u0001\u0000\u0000\u0000\u01fa\u01f8\u0001\u0000"+
		"\u0000\u0000\u01fa\u01fb\u0001\u0000\u0000\u0000\u01fb\u01fe\u0001\u0000"+
		"\u0000\u0000\u01fc\u01fa\u0001\u0000\u0000\u0000\u01fd\u01f5\u0001\u0000"+
		"\u0000\u0000\u01fd\u01fe\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000"+
		"\u0000\u0000\u01ff\u0200\u0005\u0011\u0000\u0000\u0200I\u0001\u0000\u0000"+
		"\u0000\u0201\u0209\u0005\u0002\u0000\u0000\u0202\u0203\u0005n\u0000\u0000"+
		"\u0203\u0204\u0005&\u0000\u0000\u0204\u0205\u0003F#\u0000\u0205\u0206"+
		"\u0005\u0006\u0000\u0000\u0206\u0208\u0001\u0000\u0000\u0000\u0207\u0202"+
		"\u0001\u0000\u0000\u0000\u0208\u020b\u0001\u0000\u0000\u0000\u0209\u0207"+
		"\u0001\u0000\u0000\u0000\u0209\u020a\u0001\u0000\u0000\u0000\u020a\u020c"+
		"\u0001\u0000\u0000\u0000\u020b\u0209\u0001\u0000\u0000\u0000\u020c\u020d"+
		"\u0005\u0003\u0000\u0000\u020dK\u0001\u0000\u0000\u0000\u020e\u020f\u0007"+
		"\t\u0000\u0000\u020fM\u0001\u0000\u0000\u0000\u0210\u0211\u0007\n\u0000"+
		"\u0000\u0211O\u0001\u0000\u0000\u0000)Ybi\u0087\u008b\u0093\u00a3\u00a7"+
		"\u00b0\u00b7\u00bb\u00ca\u00d2\u00d8\u00de\u00f6\u00fb\u0101\u0105\u0110"+
		"\u0121\u0126\u0165\u016a\u0178\u017e\u0184\u018a\u0194\u019a\u019f\u01a4"+
		"\u01b0\u01b5\u01cb\u01dd\u01e8\u01f2\u01fa\u01fd\u0209";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}