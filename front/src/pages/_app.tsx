import "@/styles/globals.css";
import type { AppProps } from "next/app";
import { Inter } from "next/font/google";
import { AppCacheProvider } from "@mui/material-nextjs/v13-pagesRouter";
import 'moment/locale/pt-br'

const inter = Inter({ subsets: ["latin"] });

export default function App({ Component, pageProps }: AppProps) {
  return (
    <AppCacheProvider {...pageProps}>
      <main className={`${inter.className} h-full w-full`}>
        <Component {...pageProps} />
      </main>
    </AppCacheProvider>
  );
}
