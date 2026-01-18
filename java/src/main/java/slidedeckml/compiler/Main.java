package slidedeckml.compiler;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Main {
  public static void main(String[] args) throws Exception {
    Args parsed = Args.parse(args);

    String input = new String(Files.readAllBytes(Paths.get(parsed.inputPath)), StandardCharsets.UTF_8);
    String output = new slidedeckml.compiler.bnf.BnfCompiler().compileToSlidevMarkdown(input);
    Files.write(Paths.get(parsed.outputPath), output.getBytes(StandardCharsets.UTF_8));
  }

  private static final class Args {
    final String inputPath;
    final String outputPath;

    private Args(String inputPath, String outputPath) {
      this.inputPath = inputPath;
      this.outputPath = outputPath;
    }

    static Args parse(String[] args) {
      String input = null;
      String out = null;

      for (int i = 0; i < args.length; i++) {
        String a = args[i];
        if ("-i".equals(a) || "--input".equals(a)) {
          input = requireValue(args, ++i, a);
        } else if ("-o".equals(a) || "--out".equals(a) || "--output".equals(a)) {
          out = requireValue(args, ++i, a);
        } else if ("-h".equals(a) || "--help".equals(a)) {
          usageAndExit(0);
        } else {
          System.err.println("Unknown arg: " + a);
          usageAndExit(2);
        }
      }

      if (input == null || out == null) {
        usageAndExit(2);
      }

      if (!new File(input).exists()) {
        System.err.println("Input not found: " + input);
        System.exit(2);
      }

      return new Args(input, out);
    }

    private static String requireValue(String[] args, int i, String flag) {
      if (i >= args.length) {
        System.err.println("Missing value for " + flag);
        usageAndExit(2);
      }
      return args[i];
    }

    private static void usageAndExit(int code) {
      System.out.println("Usage: java ... slidedeckml.compiler.Main -i <input.deck> -o <slides.md>");
      System.exit(code);
    }
  }
}
