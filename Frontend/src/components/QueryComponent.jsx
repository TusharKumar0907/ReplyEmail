import { useState } from "react";
import { submitQuery } from "../service/queryService";

function QueryComponent() {
  const [content, setContent] = useState("");
  const [tone, setTone] = useState("");
  const [reply, setReply] = useState("");

  const handleSubmit = () => {
    const body = { content, tone };
    submitQuery(body).then((response) => {
      setReply(response.data);
    }).catch((error) => {
      console.error("Error submitting query:", error);
    });
  };

  return (
    <div>
      <h1
        style={{
          padding: "3rem",
          marginLeft: "30rem",
        }}
      >
        Reply My Email
      </h1>

      {/* Input Fields and Submit Button */}
      <div
        style={{
          padding: "1rem",
          borderRadius: "2rem",
          border: "1px solid #ccc",
          width: "50rem",
          display: "flex",
          justifyContent: "start",
          gap: "1em",
          alignItems: "center",
          boxShadow: "0 2px 4px rgba(0, 0, 0, 0.2)",
          marginLeft: "20rem",
        }}
      >
        <input
          type="text"
          value={content}
          onChange={(e) => setContent(e.target.value)}
          placeholder="Enter your content"
          style={{
            width: "70%",
            height: "100%",
            padding: "1rem",
            border: "none",
            fontSize: "1rem",
          }}
        />

        <input
          type="text"
          value={tone}
          onChange={(e) => setTone(e.target.value)}
          placeholder="Enter tone"
          style={{
            width: "20%",
            height: "100%",
            padding: "1rem",
            border: "none",
            fontSize: "1rem",
          }}
        />

        <button
          onClick={handleSubmit}
          style={{
            cursor: "pointer",
            padding: "0.7rem 1.5rem",
            fontSize: "1rem",
            borderRadius: "0.5rem",
            border: "1px solid #ccc",
            backgroundColor: "#007bff",
            color: "white",
          }}
        >
          Submit
        </button>
      </div>

      <div style={{ padding: "1rem" }}></div>

      {/* Reply Section with Copy Button */}
      <div
        style={{
          padding: "1rem",
          borderRadius: "2rem",
          border: "1px solid #ccc",
          width: "50rem",
          display: "flex",
          justifyContent: "start",
          gap: "1em",
          alignItems: "center",
          boxShadow: "0 2px 4px rgba(0, 0, 0, 0.2)",
          marginLeft: "20rem",
        }}
      >
        <input
          type="text"
          value={reply}
          readOnly
          style={{
            width: "100%",
            height: "100%",
            padding: "1rem",
            border: "none",
            fontSize: "1rem",
            backgroundColor: "#f0f0f0", // Indicate non-editable input
          }}
        />

        <button
          onClick={() => navigator.clipboard.writeText(reply)}
          style={{
            cursor: "pointer",
            padding: "0.5rem 1rem",
            fontSize: "1rem",
            borderRadius: "0.5rem",
            border: "1px solid #ccc",
            backgroundColor: "#28a745",
            color: "white",
          }}
        >
          Copy
        </button>
      </div>
    </div>
  );
}

export default QueryComponent;
