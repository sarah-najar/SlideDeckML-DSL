package slidedeckml.compiler;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import slidedeckml.grammar.SlideDeckMLLexer;
import slidedeckml.grammar.SlideDeckMLParser;

public final class SlideDeckMLCompiler {
  public String compileToSlidevMarkdown(String input) {
    if (input != null && !input.isEmpty() && input.charAt(0) == '\uFEFF') {
      input = input.substring(1);
    }
    SlideDeckMLLexer lexer = new SlideDeckMLLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    SlideDeckMLParser parser = new SlideDeckMLParser(tokens);

    Models.Deck deck = (Models.Deck) new SlideDeckMLToModelVisitor().visit(parser.file());
    return new SlidevEmitter().emit(deck);
  }
}
