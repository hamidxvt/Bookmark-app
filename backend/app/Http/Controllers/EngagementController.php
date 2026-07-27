<?php

namespace App\Http\Controllers;

use App\Models\DailyContent;
use Illuminate\Http\Request;

class EngagementController extends Controller
{
    private array $quotes = [
        "Every school visit is an investment in Pakistan's future.",
        "Consistency beats intensity — show up every day.",
        "The best sales happen when you genuinely want to help.",
        "Your hard work today is tomorrow's results.",
        "A visit completed is a relationship built.",
        "Success is the sum of small efforts, repeated day in and day out.",
        "Be the reason a school chooses Bookmark.",
    ];

    private array $tips = [
        "Always confirm the contact person's designation before entering your notes.",
        "A follow-up scheduled today is a sale closed tomorrow.",
        "Arrive at schools before 9 AM for the best chance of meeting the principal.",
        "Note any new grades or subjects the school is planning to introduce.",
        "Collect competitor book names — it's valuable intelligence.",
        "Leave a sample; leave an impression.",
    ];

    private array $startMessages = [
        "Best of luck today! You can do it.",
        "A great day starts with a great attitude. Let's go!",
        "Every visit counts. Make them all matter.",
        "Today's hard work is tomorrow's success story.",
    ];

    private array $halfDayMessages = [
        "You're doing great — halfway to go!",
        "Keep the momentum going. You're halfway there.",
        "Great work so far! Finish strong.",
    ];

    private array $dayEndMessages = [
        "Well done! You've completed your day. See you tomorrow.",
        "Excellent work today! Your effort makes a difference.",
        "Day complete! Rest up and come back stronger tomorrow.",
    ];

    public function dailyContent()
    {
        $dayOfYear = now()->dayOfYear;

        return response()->json([
            'quote' => $this->quotes[$dayOfYear % count($this->quotes)],
            'tip' => $this->tips[$dayOfYear % count($this->tips)],
            'day_start_message' => $this->startMessages[$dayOfYear % count($this->startMessages)],
            'half_day_message' => $this->halfDayMessages[$dayOfYear % count($this->halfDayMessages)],
            'day_end_message' => $this->dayEndMessages[$dayOfYear % count($this->dayEndMessages)],
        ]);
    }
}
