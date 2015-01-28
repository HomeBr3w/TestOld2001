
#ifndef _SONG_HEADER_
#define _SONG_HEADER_

#include "Note.h"
#include <vector>

class Song
{
private:
	std::vector<Note> notes;

public:
	Song();
	~Song();
	void addNote(Note note);
	void removeLastNote();
	void removeFirstNote();
	void removeNoteAt(int index);
	void removeNote(Note note);
};

#endif