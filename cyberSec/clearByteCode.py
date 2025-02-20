import os

def delete_class_files(root_dir):
    deleted_files = 0
    
    # Walk through all subdirectories and files
    for foldername, subfolders, filenames in os.walk(root_dir):
        for filename in filenames:
            if filename.endswith(".class"):
                file_path = os.path.join(foldername, filename)
                try:
                    os.remove(file_path)
                    deleted_files += 1
                    print(f"Deleted: {file_path}")
                except Exception as e:
                    print(f"Failed to delete {file_path}: {e}")
    
    if deleted_files == 0:
        print("No .class files found.")
    else:
        print(f"Total .class files deleted: {deleted_files}")

# Get the directory where the script is placed
root_directory = os.path.dirname(os.path.abspath(__file__))

# Run the cleanup
delete_class_files(root_directory)
