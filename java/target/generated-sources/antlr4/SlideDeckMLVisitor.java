// Generated from SlideDeckML.g4 by ANTLR 4.13.1
package slidedeckml.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SlideDeckMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SlideDeckMLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(SlideDeckMLParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#deckDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeckDecl(SlideDeckMLParser.DeckDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#deckItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeckItem(SlideDeckMLParser.DeckItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#headDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeadDecl(SlideDeckMLParser.HeadDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#headProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeadProp(SlideDeckMLParser.HeadPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#templateDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateDecl(SlideDeckMLParser.TemplateDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#templateProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateProp(SlideDeckMLParser.TemplatePropContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#layoutDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayoutDecl(SlideDeckMLParser.LayoutDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#layoutKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayoutKind(SlideDeckMLParser.LayoutKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#layoutZone}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayoutZone(SlideDeckMLParser.LayoutZoneContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#area}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArea(SlideDeckMLParser.AreaContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#slideDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlideDecl(SlideDeckMLParser.SlideDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#slideFrontProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlideFrontProp(SlideDeckMLParser.SlideFrontPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#transitionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionValue(SlideDeckMLParser.TransitionValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#transitionObjProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitionObjProp(SlideDeckMLParser.TransitionObjPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#slideBodyItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlideBodyItem(SlideDeckMLParser.SlideBodyItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#slotDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlotDecl(SlideDeckMLParser.SlotDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#markdownDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarkdownDecl(SlideDeckMLParser.MarkdownDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#elementDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementDecl(SlideDeckMLParser.ElementDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#elementType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementType(SlideDeckMLParser.ElementTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#elementProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementProp(SlideDeckMLParser.ElementPropContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#positionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionDecl(SlideDeckMLParser.PositionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#absolutePosition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsolutePosition(SlideDeckMLParser.AbsolutePositionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#relativePosition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelativePosition(SlideDeckMLParser.RelativePositionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#animationDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnimationDecl(SlideDeckMLParser.AnimationDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#animationKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnimationKind(SlideDeckMLParser.AnimationKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit(SlideDeckMLParser.UnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#anchorType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnchorType(SlideDeckMLParser.AnchorTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#align}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlign(SlideDeckMLParser.AlignContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#imageFit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageFit(SlideDeckMLParser.ImageFitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#equationDisplayMode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquationDisplayMode(SlideDeckMLParser.EquationDisplayModeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#stepDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepDecl(SlideDeckMLParser.StepDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#stepItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepItem(SlideDeckMLParser.StepItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(SlideDeckMLParser.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#valueList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueList(SlideDeckMLParser.ValueListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SlideDeckMLParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(SlideDeckMLParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#obj}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObj(SlideDeckMLParser.ObjContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(SlideDeckMLParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlideDeckMLParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(SlideDeckMLParser.BoolContext ctx);
}