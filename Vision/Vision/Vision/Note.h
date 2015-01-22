
const enum NoteType { A, B, C, D, E, F, G, PAUSE,  };

class Note
{
private:
	
	float duration;
	NoteType type;
	float durationModifier;
	float typeModifier;
public:
	Note(NoteType _type, int _duration, float _modifier);
	~Note();
	void setDurationModifier(float durationModifier);
	void setTypeModifier(float typeModifier);
	void setType(NoteType type);
	void setDuration(float duration);
};