import 'dart:math';

const List<String> _salesQuotes = [
  '"Every no gets you closer to a yes." — Sales wisdom',
  '"Your attitude, not your aptitude, determines your altitude." — Zig Ziglar',
  '"Success is the sum of small efforts, repeated day in and day out." — Robert Collier',
  '"Don\'t watch the clock; do what it does. Keep going." — Sam Levenson',
  '"The secret of getting ahead is getting started." — Mark Twain',
  '"Champions keep playing until they get it right." — Billie Jean King',
  '"A goal is a dream with a deadline." — Napoleon Hill',
  '"Every customer interaction is a chance to make a lasting impression."',
  '"Your morning routine sets the tone for your entire day. Start strong."',
  '"The difference between ordinary and extraordinary is that little extra."',
  '"Believe you can and you\'re halfway there." — Theodore Roosevelt',
  '"Quality beats quantity. One genuine visit beats five rushed ones."',
  '"Your reputation is built one school at a time. Make it count today."',
  '"Today\'s preparation determines tomorrow\'s achievement."',
  '"Hustle in silence. Let your results make the noise."',
];

const List<String> _eveningQuotes = [
  '"Well done is better than well said." — Benjamin Franklin',
  '"Every day is a chance to be better than yesterday."',
  '"The journey of a thousand miles begins with a single step." — Lao Tzu',
  '"Success is not final, failure is not fatal: it is the courage to continue." — Churchill',
  '"Hard work beats talent when talent doesn\'t work hard." — Tim Notke',
  '"Celebrate your wins, no matter how small. Progress is progress."',
  '"Rest is not idleness — it\'s fuel for tomorrow\'s greatness."',
  '"You\'ve put in the work today. The results will follow."',
  '"Each visit you made today planted a seed for tomorrow\'s harvest."',
  '"Great salespeople are made, not born. You\'re becoming one every single day."',
];

String getDayStartQuote() {
  final rng = Random();
  return _salesQuotes[rng.nextInt(_salesQuotes.length)];
}

String getDayEndQuote() {
  final rng = Random();
  return _eveningQuotes[rng.nextInt(_eveningQuotes.length)];
}
